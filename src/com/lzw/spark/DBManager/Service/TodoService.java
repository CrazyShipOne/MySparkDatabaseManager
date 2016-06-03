package com.lzw.spark.DBManager.Service;


import com.google.gson.Gson;
import com.lzw.spark.DBManager.Tools.MongoTools;
import com.lzw.spark.DBManager.pojo.Todo;
import com.mongodb.*;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by lzw on 2016/5/31.
 */
public class TodoService {
    private DB db;
    private DBCollection collection;

    public TodoService()
    {
        this.db = MongoTools.getAuthenticatedMongoDB("todo","test","test");
        this.collection = db.getCollection("todos");
    }

    public List<Todo> findAll()
    {
        List<Todo> todos = new ArrayList<>();
        DBCursor dbObjects = collection.find();
        while(dbObjects.hasNext())
        {
            DBObject object = dbObjects.next();
            todos.add(new Todo((BasicDBObject)object));
        }
        return todos;
    }

    public void createTodos(String body)
    {
        Todo todo = new Gson().fromJson(body,Todo.class);
        collection.insert(new BasicDBObject("title",todo.getTitle()).append("done",todo.isDone()).append("createdOn",new Date()));
    }

    public Todo findOne(String id)
    {
        return new Todo((BasicDBObject)collection.findOne(new BasicDBObject("_id",new ObjectId(id))));
    }

    public Todo update(String id,String body)
    {
        Todo todo = new Gson().fromJson(body,Todo.class);
        collection.update(new BasicDBObject("_id",new ObjectId(id)),new BasicDBObject("$set",new BasicDBObject("done",todo.isDone())));
        return this.findOne(id);
    }
}
