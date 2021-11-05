package com.example.photo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.photo.dao.ImageAttribute;
import com.example.photo.imagesutil.LocalFileConvertObjectUtil;
import com.example.photo.service.IImageInitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/token")
public class ImageDownloadController {

    @Autowired
    IImageInitService imageInitService;

    @RequestMapping("{id}")
    @ResponseBody
    public String download(@PathVariable Integer id) throws Exception {

        List<ImageAttribute> attributes = new ArrayList<ImageAttribute>();
        String result = imageInitService.getImageInitMap().get(id.toString());
        return result;
    }

//    @RequestMapping("{id}")
//    public void  download(@PathVariable Integer id, HttpServletRequest request, HttpServletResponse response) throws Exception {
//        request.getRequestDispatcher("/images/"+id+".png").forward(request,response);
//    }
}
