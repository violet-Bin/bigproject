package com.jd.gms.mongo.connect;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.data.mongodb.core.MongoOperations;

/**
 * @Author: jiangjiabin3
 * @Date: Created in 2019/12/11 16:51
 * @Description: 创建集合、插入文档、查找文档、修改文档、删除文档
 */
public class BaseOperate {

    public static void main(String[] args) {
        try {
            MongoClient client = new MongoClient("localhost", 27017);
            MongoDatabase db = client.getDatabase("test");
            MongoCollection<Document> coll = db.getCollection("coll");
            System.out.println(coll);

            //插入文档
            Document doc = new Document("title", "redis")
                    .append("description", "ddd")
                    .append("likes", 333)
                    .append("by", "sss");
            coll.insertOne(doc);
            FindIterable<Document> documents = coll.find();
            find(documents);
//            updateOne(coll);
            updateMany(coll);
//            delete(coll);
//            deleteMany(coll);
            find(documents);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void find(FindIterable<Document> documents) {
        MongoCursor<Document> cursor = documents.iterator();
        int i = 0;
        while (cursor.hasNext()) {
            System.out.println(i++);
            System.out.println(cursor.next());
        }
        System.out.println("=========================================");
    }

    public static void updateOne(MongoCollection<Document> coll) {
        Document doc = new Document();
        Document filter = new Document("likes", 222);
        doc.put("title", "bbbbbbbbbbbbbb");
        coll.updateOne(filter, new Document("$set", doc));
    }

    public static void updateMany(MongoCollection<Document> coll) {
        Document doc = new Document();
        Document filter = new Document("likes", 123).append("by", "sss");
        doc.put("title", "ccccccccccccccccc");
        coll.updateMany(filter, new Document("$set", doc));
    }

    public static void delete(MongoCollection<Document> coll) {
        Document filter = new Document("likes", 222);
        coll.deleteOne(filter);
    }

    public static void deleteMany(MongoCollection<Document> coll) {
        Document filter = new Document("likes", 222);
        coll.deleteMany(filter);
    }


}

