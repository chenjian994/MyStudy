package com.cdvtc.mongodbstudy;

import com.mongodb.*;

import java.net.UnknownHostException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: chenjian
 * Date: 13-10-15
 * Time: 上午10:24
 * To change this template use File | Settings | File Templates.
 */
public class MongoDB01 {
    public static void main(String[] args) {
        try {
            MongoClient mongoClient = new MongoClient("localhost", 27017);
            DB db = mongoClient.getDB("mydb");
            Set<String> cols = db.getCollectionNames();
            System.out.println("所有Collection的名字：");
            for (String s : cols) {
                System.out.println(s);
            }

            DBCollection collection = db.getCollection("testData");
            List l = collection.distinct("name");
            Iterator iter = l.iterator();
            while (iter.hasNext()) {
                System.out.print("去除重复的：" + iter.next() + " _ ");
            }
            System.out.println();

            BasicDBObject doc = new BasicDBObject("name", "MongoDB")
                    .append("type", "database")
                    .append("count", 1)
                    .append("info", new BasicDBObject("x", 203).append("y", 102));
            WriteResult wr = collection.insert(doc);

            DBObject myDoc = collection.findOne();
            System.out.println("第一条记录：" + myDoc);

            System.out.println("总记录数：" + collection.count());


            System.out.println("使用DBCursor类遍历Collection:");
            DBCursor cursor = collection.find();
            while (cursor.hasNext()) {
                System.out.println(cursor.next());
            }
            cursor.close();

            System.out.println("指定查找：");
            BasicDBObject query = new BasicDBObject("name", "MongoDB");
            cursor = collection.find(query);
            while (cursor.hasNext()) {
                System.out.println(cursor.next());
            }

            for (int i = 0; i < 50; i++) {
                collection.insert(new BasicDBObject("i", i).append("j", i).append("k", i));
            }

            cursor = collection.find(new BasicDBObject("j", new BasicDBObject("$ne", 3)).append("k", new BasicDBObject("$gt", 10)));
            System.out.println("____");
            while (cursor.hasNext()) {
                System.out.print(cursor.next() + " _ ");
            }
            cursor.close();

            System.out.println();
            System.out.println("打印j大于45的：");
            cursor = collection.find(new BasicDBObject("j", new BasicDBObject("$gt", 45)));
            while (cursor.hasNext()) {
                System.out.println(cursor.next());
            }
            cursor.close();

            System.out.println("打印出j大于20小于等于30的：");
            cursor = collection.find(new BasicDBObject("j", new BasicDBObject("$gt", 20).append("$lte", 30)));
            while(cursor.hasNext()) {
                System.out.println(cursor.next());
            }
            cursor.close();

            collection.createIndex(new BasicDBObject("i", 1));

            System.out.println("创建的index:");
            List<DBObject> list = collection.getIndexInfo();
            for(DBObject dbo : list) {
                System.out.println(dbo);
            }

            collection.drop();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
