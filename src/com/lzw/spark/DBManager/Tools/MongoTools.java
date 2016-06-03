package com.lzw.spark.DBManager.Tools;

import com.mongodb.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by lzw on 16-6-3.
 */

/**
 * MongoDB工具类
 */
public class MongoTools {
    //todo:以后改成从配置文件读取数据库地址和端口
    private static String host = "127.0.0.1";
    private static int port = 27017;

    private static MongoClient mongoClient ;
    private static Logger log = LoggerFactory.getLogger(MongoTools.class);


    /**
     * 获取一个数据库
     * @param dbName  数据库名称
     * @return
     */
    public static synchronized DB getMongoDB(String dbName)
    {
        if(mongoClient == null)
        {
            try {
                mongoClient = new MongoClient(host, port);
                mongoClient.setWriteConcern(WriteConcern.SAFE);
                return mongoClient.getDB(dbName);
            }
            catch (Exception ex)
            {
                log.error("获取数据库{}连接错误！",dbName);
            }
        }
        else
        {
            return mongoClient.getDB(dbName);
        }
        return null;
    }

    /**
     * 获取一个需要验证的数据库
     * @param dbName  数据库名称
     * @param user   用户名
     * @param pwd   密码
     * @return
     */
    public static synchronized DB getAuthenticatedMongoDB(String dbName,String user,String pwd)
    {
        if(mongoClient == null)
        {
            try {
                mongoClient = new MongoClient(host, port);
                mongoClient.setWriteConcern(WriteConcern.SAFE);
                DB db = mongoClient.getDB(dbName);
                if(db.authenticate(user, pwd.toCharArray())) {
                    return db;
                }
                else
                {
                    return null;
                }
            }
            catch (Exception ex)
            {
                log.error("获取数据库{}连接错误！",dbName);
            }
        }
        else
        {
            DB db = mongoClient.getDB(dbName);
            if(!db.isAuthenticated() && db.authenticate(user, pwd.toCharArray())) {
                return db;
            }
            else
            {
                return null;
            }
        }
        return null;
    }
}
