package com.lzw.spark.DBManager.Resource;

import com.lzw.spark.DBManager.Service.TodoService;

import com.lzw.spark.DBManager.Tools.JsonTransformer;
import spark.Request;
import spark.Response;

import static spark.Spark.*;

/**
 * Created by lzw on 2016/5/31.
 */
public class TodoResource {
    private static final String API_CONTEXT = "/api/v1";

    private final TodoService todoService;

    public TodoResource(TodoService todoService) {
        this.todoService = todoService;
        setupEndpoints();
    }

    private void setupEndpoints() {
        post(API_CONTEXT + "/todos", "application/json", (Request request, Response response) -> {
            todoService.createTodos(request.body());
            response.status(201);
            return response;
        }, new JsonTransformer());

        get(API_CONTEXT + "/todos/:id", "application/json", (Request request, Response response)

                -> todoService.findOne(request.params(":id")), new JsonTransformer());

        get(API_CONTEXT + "/todos", "application/json", (request, response)

                -> todoService.findAll(), new JsonTransformer());

        put(API_CONTEXT + "/todos/:id", "application/json", (Request request, Response response)

                -> todoService.update(request.params(":id"), request.body()), new JsonTransformer());
    }
}
