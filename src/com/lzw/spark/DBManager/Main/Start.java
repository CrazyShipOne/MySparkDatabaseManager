package com.lzw.spark.DBManager.Main;

/**
 * Created by lzw on 2016/5/31.
 */
import com.lzw.spark.DBManager.Resource.TodoResource;
import com.lzw.spark.DBManager.Service.TodoService;
import com.mongodb.*;

import java.net.UnknownHostException;

import static spark.Spark.*;

public class Start {
    public static void main(String[] args) {
        port(12345);
        staticFileLocation("/public");
        new TodoResource(new TodoService(mongo()));
    }

    private static DB mongo(){
        try {
            int port = 27017;
            String dbname = "todo";
            String username = "test";
            String password = "test";
            MongoClientOptions options = MongoClientOptions.builder().build();
            MongoClient client = new MongoClient(new ServerAddress("0.0.0.0",port),options);
            client.setWriteConcern(WriteConcern.SAFE);
            DB db = client.getDB(dbname);
            if(db.authenticate(username,password.toCharArray()))
            {
                return db;
            }
            else
            {
                return null;
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return null;
    }
}
