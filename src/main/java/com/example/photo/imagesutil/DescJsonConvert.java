package com.example.photo.imagesutil;

import com.alibaba.fastjson.JSONObject;
import com.example.photo.dao.ImageAttribute;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DescJsonConvert {

    public static void main(String[] args) {
        Map<String,Object> localMap = null;
        try {
            localMap = LocalFileConvertObjectUtil.getFileObject(Map.class,"D:/images/history.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Map<String,String> newDatas = new HashMap<>();
        String description="Hello and welcome to 333 Leopard Club, there are 3333 leopards waitting for you, each leopard reflects a different vibe and meaning, so choose one that you love most.";
        int i=0;
        for(String key:localMap.keySet()){
            Map<String,Object> data2 = new HashMap<>();
            List<ImageAttribute> attributes =  JSONObject.parseObject(key, List.class);
            Object value = localMap.get(key);
            data2.put("name","Leopard #"+(i+1));
            data2.put("attributes",attributes);
            data2.put("description",description);
            String url = "https://333leopard.club/images/"+value+".png";
            data2.put("image",url);
            String des  = JSONObject.toJSONString(data2).replaceAll("\\\\","");
            newDatas.put(i+"",des);
            i++;
        }
        try {
            LocalFileConvertObjectUtil.setFileObject(newDatas,"D:/images/out.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
