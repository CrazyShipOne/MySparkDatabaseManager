package com.lzw.spark.DBManger.Main;

/**
 * Created by lzw on 2016/5/31.
 */
import static spark.Spark.*;

public class Start {
    public static void main(String[] args) {
        port(12345);
        get("/", (req, res) -> {
            String abc = req.queryParams("test");
            return abc;
        });
    }
}
