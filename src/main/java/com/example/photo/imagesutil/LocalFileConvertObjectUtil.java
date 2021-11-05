package com.example.photo.imagesutil;

import com.alibaba.fastjson.JSONObject;

import java.io.*;

public class LocalFileConvertObjectUtil {

    public static void setFileObject(Object object, String outputDir) throws IOException {
        File file = new File(outputDir);
        if (!file.exists()) {
            file.createNewFile();
        }
        String str = JSONObject.toJSONString(object);
        char[] chars = str.toCharArray();
        BufferedWriter out = new BufferedWriter(new FileWriter(file));
        for(int i =0;i<chars.length;i++){
            out.write(chars[i]);
        }
        out.close();
    }

    public static <T>T getFileObject(Class<T> data, String inputDir) throws IOException {
        File file = new File(inputDir);
        if (!file.exists() || file.length()==0) {
            file.createNewFile();
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader in = new BufferedReader(new FileReader(file));
        String str;
        while ((str = in.readLine()) != null)
        {
            stringBuilder.append(str);
        }
        in.close();
        return (T) JSONObject.parseObject(stringBuilder.toString(),data);
    }
}
