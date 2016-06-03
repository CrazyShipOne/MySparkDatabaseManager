package com.lzw.spark.DBManager.Business;

import com.lzw.spark.DBManager.Resource.ServiceMaintainer;
import com.lzw.spark.DBManager.Service.TodoService;
import com.lzw.spark.DBManager.Resource.BasicBusiness;
import com.lzw.spark.DBManager.Tools.MongoTools;
import spark.Request;
import spark.Response;

/**
 * Created by lzw on 16-6-3.
 */
public class TodoBusiness extends BasicBusiness{

    public TodoBusiness(Request req, Response res) {
        super(req, res);
    }

    public Object createTodo() {
        TodoService todoService = (TodoService)ServiceMaintainer.getServices(TodoService.class);
        todoService.createTodos(req.body());
        res.status(201);
        return res;
    }

    public Object findOneTodo()
    {
        TodoService todoService = (TodoService)ServiceMaintainer.getServices(TodoService.class);
        return todoService.findOne(req.params(":id"));
    }

    public Object findAllTodo()
    {
        TodoService todoService = (TodoService)ServiceMaintainer.getServices(TodoService.class);
        return todoService.findAll();
    }

    public Object updateTodo()
    {
        TodoService todoService = (TodoService)ServiceMaintainer.getServices(TodoService.class);
        return todoService.update(req.params(":id"), req.body());
    }

}
