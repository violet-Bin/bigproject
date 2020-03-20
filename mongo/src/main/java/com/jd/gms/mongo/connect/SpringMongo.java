package com.jd.gms.mongo.connect;

import com.jd.gms.mongo.connect.bean.TestBean;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/**
 * @Author: jiangjiabin3
 * @Date: Created in 2019/12/12 11:08
 * @Description:
 */
public class SpringMongo {

    public static void main(String[] args) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {


        TestBean testBean = new TestBean();
        testBean.setTitle("ttttt");
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-config.xml");
        MongoOperations mongoDao = (MongoOperations) context.getBean("mongoTemplate");

        /*
        //查找固定字段，并将值设置到对象中返回
        BasicDBObject filter = new BasicDBObject("likes", 333);
        BasicDBObject field = new BasicDBObject("likes", 1).append("_id", 0);
        DBCursor cursor = mongoDao.getCollection("coll").find(filter, field);
        while (cursor.hasNext()) {
            DBObject dbObject = cursor.next();
            dbObject2Bean(testBean, dbObject);
            System.out.println(testBean.toString());
        }

        findALlAndPrint(mongoDao, "coll");
8*/
        //批量更新  一个过滤条件
        BasicDBObject updateFilter = new BasicDBObject("likes", 333).append("by", "sss");
        BasicDBObject updateObject = new BasicDBObject();
        updateObject.put("title", "new title---aaaaaa");
        updateObject.put("description", "descriptiondescription");
        //new BasicDBObject("$set", updateObject)  更新时，不这样写，其他字段都会丢失
//        mongoDao.getCollection("coll").updateMulti(updateFilter, new BasicDBObject("$set", updateObject));//这个方法不会插入新值
        mongoDao.getCollection("coll").update(updateFilter, new BasicDBObject("$set", updateObject), true, true);


        findALlAndPrint(mongoDao, "coll");

    }

    /**
     * find all and print
     *
     * @param mongoDao
     * @param coll
     */
    private static void findALlAndPrint(MongoOperations mongoDao, String coll) {
        DBCursor cursor = mongoDao.getCollection(coll).find();
        while (cursor.hasNext()) {
            DBObject dbObject = cursor.next();
            System.out.println(dbObject);
        }
        System.out.println("=========================");
    }

    /**
     * DBObject to bean
     *
     * @param dbObject DBObject
     * @return bean
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws NoSuchMethodException
     */
    public static TestBean dbObject2Bean(TestBean testBean, DBObject dbObject) throws IllegalAccessException,
            InvocationTargetException, NoSuchMethodException {
        if (null == dbObject) {
            return null;
        }
        Field[] fields = testBean.getClass().getDeclaredFields();
        for (Field field : fields) {
            String varName = field.getName();
            Object object = dbObject.get(varName);
            if (object != null) {
                BeanUtils.setProperty(testBean, varName, object);
            }
        }
        return testBean;
    }

}
