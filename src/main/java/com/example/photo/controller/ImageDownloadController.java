package com.example.photo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/token")
public class ImageDownloadController {

    @RequestMapping("{id}")
    public void download(@PathVariable Integer id, HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.getRequestDispatcher("/images/"+id+".png").forward(request,response);
    }

}
