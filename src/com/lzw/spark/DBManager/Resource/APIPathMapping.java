package com.lzw.spark.DBManager.Resource;

import com.lzw.spark.DBManager.Business.TodoBusiness;
import com.lzw.spark.DBManager.Tools.JsonTransformer;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.freemarker.FreeMarkerEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

/**
 * Created by lzw on 16-6-3.
 */

/**
 * 程序启动入口，定义api的请求路径
 */
public class APIPathMapping {
    private static final String API_CONTEXT = "/api/v1";
    private static final int port = 12345;
    private static final String fileLocation = "/public";
    private static final Map<String,Object[]> maps = new HashMap<>();

    public APIPathMapping() {
        defMapping();
        setupEndpoints();
    }

    /**
     * 定义请求路径
     */
    private void defMapping()
    {
        //key为路径，value集合格式为[方法，处理类，处理方法]
        maps.put("/todos",new Object[]{"post",TodoBusiness.class,"createTodo"});
        maps.put("/todos/:id",new Object[]{"get",TodoBusiness.class,"findOneTodo"});
        maps.put("/todos",new Object[]{"get",TodoBusiness.class,"findAllTodo"});
        maps.put("/todos/:id",new Object[]{"put",TodoBusiness.class,"updateTodo"});
    }

    /**
     * 遍历maps，创建请求路径
     */
    private void setupEndpoints() {
        staticFileLocation(fileLocation);
        port(port);
        for(Map.Entry<String,Object[]> entry: maps.entrySet())
        {
            String path = entry.getKey();
            Object[] objs = entry.getValue();
            if("post".equals((String) objs[0]))
            {
                post(API_CONTEXT +path, "application/json", (Request request, Response response) ->
                        RequestHandler.getResult((Class)objs[1],(String)objs[2], request, response), new JsonTransformer());
            }
            else if("get".equals((String) objs[0]))
            {
                get(API_CONTEXT + path, "application/json", (Request request, Response response) ->
                        RequestHandler.getResult((Class) objs[1], (String) objs[2], request, response), new JsonTransformer());
            }
            else if("put".equals((String) objs[0]))
            {
                put(API_CONTEXT + path, "application/json", (Request request, Response response) ->
                        RequestHandler.getResult((Class) objs[1], (String) objs[2], request, response), new JsonTransformer());
            }
        }

        get("/test", (req, rep) -> {
            Map<String, Object> attrs = new HashMap<String, Object>();
            attrs.put("message", "hello");
            return new ModelAndView(attrs, "hello.ftl");
        }, new FreeMarkerEngine());
    }
}
