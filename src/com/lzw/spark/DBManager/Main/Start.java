package com.lzw.spark.DBManager.Main;

/**
 * Created by lzw on 2016/5/31.
 */

import com.lzw.spark.DBManager.Resource.APIPathMapping;

import static spark.Spark.*;

public class Start {
    public static void main(String[] args) {
        staticFileLocation("/public");
        new APIPathMapping();
    }

}
