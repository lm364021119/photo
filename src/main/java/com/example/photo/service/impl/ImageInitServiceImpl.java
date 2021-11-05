package com.example.photo.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.example.photo.dao.ImageAttribute;
import com.example.photo.imagesutil.LocalFileConvertObjectUtil;
import com.example.photo.service.IImageInitService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ImageInitServiceImpl implements IImageInitService {

    @Value("${images.location.init}")
    String location;

    @Value("${images.web.url}")
    String imageUrl;

    @Value("${images.description}")
    String description;

    Map<String,String> datas ;
    @Override
    public Map<String, String> getImageInitMap() {
        if(datas !=null && !datas.isEmpty()){
            return datas;
        }
        synchronized (this){
            if(datas !=null && !datas.isEmpty()){
                return datas;
            }
            try {
                datas = LocalFileConvertObjectUtil.getFileObject(Map.class,location);
            } catch (IOException e) {
                e.printStackTrace();
            }

            return datas;
        }
    }
}
