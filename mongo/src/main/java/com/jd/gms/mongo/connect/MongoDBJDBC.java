package com.jd.gms.mongo.connect;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

/**
 * @Author: jiangjiabin3
 * @Date: Created in 2019/12/11 16:10
 * @Description:
 */
public class MongoDBJDBC {

    public static void main(String[] args) {
        try {

            //连接到mongo服务器
            MongoClient client = new MongoClient("localhost", 27017);
            //连接到数据库
            MongoDatabase db = client.getDatabase("test");
            System.out.println("connect to db successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
