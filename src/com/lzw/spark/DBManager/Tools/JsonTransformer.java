package com.lzw.spark.DBManager.Tools;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import spark.ResponseTransformer;

/**
 * Created by lzw on 2016/5/31.
 */
public class JsonTransformer implements ResponseTransformer {

    @Override
    public String render(Object model) {
//        return gson.toJson(model);
        if(model == null)
        {
            return "{}";
        }
        return JSON.toJSONString(model);
    }
}