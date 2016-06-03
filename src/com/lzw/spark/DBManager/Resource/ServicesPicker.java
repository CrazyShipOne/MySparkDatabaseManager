package com.lzw.spark.DBManager.Resource;

import com.lzw.spark.DBManager.Tools.PackageUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLDecoder;
import java.util.*;

/**
 * Created by lzw on 16-6-3.
 */
public class ServicesPicker {
    private static Map<String,Method> services;
    private static String packageName = "com.lzw.spark.DBManager.Business";
    private static Logger log  = LoggerFactory.getLogger(ServicesPicker.class);

    public static synchronized Map<String,Method> getServices()
    {
        try {
            if (services == null) {
                services = new HashMap<>();
                List<String> list = PackageUtils.getClassNameByPackage(packageName);
                for (String classname : list) {
                    Class c = Class.forName(classname);
                    Method[] ms = c.getDeclaredMethods();
                    for(Method method : ms)
                    {
                        services.put(c.getSimpleName()+"."+method.getName(),method);
                    }
                    return services;
                }
            } else {
                return services;
            }
        }
        catch (Exception ex)
        {
            log.error(ex.getMessage());
        }
        return null;
    }


}
