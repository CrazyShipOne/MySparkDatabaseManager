package com.lzw.spark.DBManager.Tools;

import java.io.File;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by lzw on 16-6-3.
 */

/**
 * 包工具类
 */
public class PackageUtils {

    /**
     * 通过包名获取包下所有类名
     * @param packageName
     * @return
     */
    public static List<String> getClassNameByPackage(String packageName)
    {
        List<String> fileNames = null;
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        String packagePath = packageName.replace(".", "/");
        URL url = loader.getResource(packagePath);
        if (url != null) {
            String type = url.getProtocol();
            if (type.equals("file")) {
                return fileNames = getClassNameByFile(packageName, URLDecoder.decode(url.getPath()), null);
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    /**
     * 通过文件路径地址获取包下所有类名
     * @param packageName   包名
     * @param filePath      路径
     * @param className
     * @return
     */
    public static List<String> getClassNameByFile(String packageName, String filePath, List<String> className) {
        Properties properties = new Properties(System.getProperties());
        String FileSeparator = properties.getProperty("file.separator");
        List<String> myClassName = new ArrayList<String>();
        File file = new File(filePath);
        File[] childFiles = file.listFiles();
        for (File childFile : childFiles) {
            if (childFile.isDirectory()) {
                myClassName.addAll(getClassNameByFile(packageName,childFile.getPath(), myClassName));
            } else {
                String childFilePath = childFile.getPath();
                if (childFilePath.endsWith(".class")) {
                    int lastIndex = -1;
                    if (FileSeparator.equals("/")) {
                        lastIndex = childFilePath.lastIndexOf('/');
                    }else if (FileSeparator.equals("\\")) {
                        lastIndex = childFilePath.lastIndexOf('\\');
                    }else{
                        lastIndex = childFilePath.lastIndexOf('/');
                    }
                    int endIndex = childFilePath.lastIndexOf(".class");
                    String busServlet = childFilePath.substring(lastIndex+1,endIndex);
                    myClassName.add(packageName + "." + busServlet);
                }
            }
        }

        return myClassName;
    }
}
