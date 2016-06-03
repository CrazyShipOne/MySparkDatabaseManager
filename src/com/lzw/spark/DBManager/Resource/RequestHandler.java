package com.lzw.spark.DBManager.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Request;
import spark.Response;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * Created by lzw on 16-6-3.
 */
public class RequestHandler {
    private static Logger log = LoggerFactory.getLogger(RequestHandler.class);

    /**
     * 调用处理类，为请求获取处理结果
     * @param c 处理请求的类
     * @param businessName   处理请求的方法名称
     * @param req  Request
     * @param res  Response
     * @return
     */
    @SuppressWarnings("unchecked")
    public static Object getResult(Class c,String businessName, Request req, Response res)
    {
        Map<String,Method> services = ServicesPicker.getServices();
        Method method = services.get(c.getSimpleName()+"."+businessName);
        if(method == null)
        {
            log.error("Can not fount the method"+c.getSimpleName()+"."+businessName+"!");
            return null;
        }
        try {
            Class[] paramsType = {Request.class,Response.class};
            Object[] params = {req,res};
            Constructor con = c.getConstructor(paramsType);
            Object obj = con.newInstance(params);
            return method.invoke(obj);
        }
        catch (Exception ex)
        {
            log.error(ex.getMessage());
        }
        return null;
    }
}
