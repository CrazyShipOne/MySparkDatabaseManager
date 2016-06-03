package com.lzw.spark.DBManager.Resource;

import spark.Request;
import spark.Response;

/**
 * Created by lzw on 16-6-3.
 */

/**
 * 所有Business类的基类，传递request和response
 */
public class BasicBusiness {
    protected Request req;
    protected Response res;
    public BasicBusiness(Request req, Response res)
    {
        this.req = req;
        this.res = res;
    }
}
