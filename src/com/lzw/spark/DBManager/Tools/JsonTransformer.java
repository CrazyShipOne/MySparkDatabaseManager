package com.lzw.spark.DBManager.Tools;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import spark.ResponseTransformer;

/**
 * Created by lzw on 2016/5/31.
 */
public class JsonTransformer implements ResponseTransformer {

    private Gson gson = new Gson();

    @Override
    public String render(Object model) {
//        return gson.toJson(model);
        return JSON.toJSONString(model);
    }
}