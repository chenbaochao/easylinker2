package com.easylinker.iot.v2.utils;

/**
 * Created by wwhai on 2017/11/25.
 */

import com.easylinker.iot.v2.EasyLinker2Application;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * Created by wwhai on 2017/10/6.
 */
public class FilePathHelper {

    // 判断文件夹是否存在
    public static void dirExists(File file) {
        if (!file.getParentFile().exists()) {
            try {
                file.getParentFile().mkdirs();
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public static String getRootPath(){
        URL base = EasyLinker2Application.class.getResource("/");
        String path = new File(base.getPath()).getParent();
        return path;
    }

}