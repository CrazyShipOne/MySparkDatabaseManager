package com.lzw.spark.DBManager.Resource;

import com.lzw.spark.DBManager.Tools.PackageUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lzw on 16-6-3.
 */

/**
 * 为所有Service类创建实例，实现Service类的单例模式
 */
public class ServiceMaintainer {
    private static Map<Class,Object> services;
    private static String packageName = "com.lzw.spark.DBManager.Service";
    private static Logger log = LoggerFactory.getLogger(ServiceMaintainer.class);

    public static synchronized Object getServices(Class cls)
    {
        if(services == null)
        {
            services = new HashMap<>();
            List<String> classes = PackageUtils.getClassNameByPackage(packageName);
            for(String str : classes)
            {
                try
                {
                    Class c = Class.forName(str);
                    services.put(c,c.newInstance());
                }
                catch (Exception ex)
                {
                    log.error("Can not find class "+str);
                }
            }
        }
        return services.get(cls);
    }
}
