package com.example.photo.imagesutil;

import com.alibaba.fastjson.JSONObject;
import com.example.photo.dao.ImageAttribute;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.List;
import java.util.*;

public class CreateImagesServ {
    static String  inputDir = "D:\\images\\sucai\\";
    static String  outputDir = "D:\\images\\output\\";
    static String[] attrDirs = {"Background","Body","Clothing","Ears","Eyes","Hat","Neck","Style"};
    static String historyDir = "D:\\images\\history.txt";
    static int total =3400;
    static int width=1240;
    static int height=1240;
    public static void main(String[] args) {
        try {
            run2();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void run2() throws IOException {
        List<Map<String, String>> list = LocalFileConvertObjectUtil.getFileObject(List.class,"D:\\images\\list.txt");
        Map<String,Object> localMap = LocalFileConvertObjectUtil.getFileObject(Map.class,historyDir);
        if(localMap==null){
            localMap = new HashMap<String,Object>();
        }
        File root = new File(outputDir+"");
        int maxoutput = root.listFiles().length;
        int i =0;
       for(Map<String, String> map:list) {
           List<File> fileNames = new ArrayList<>();
           List<ImageAttribute> attributes = new ArrayList<>();
           for(String dir:attrDirs){
               String fname = map.get(dir);
               if(fname ==null ||"".equals(null)){
                   continue;
               }
               File file = new File(inputDir+dir+"\\"+fname);
               fileNames.add(file);
               int s = fname.indexOf("-");
               if(s>0){
                   fname =  fname.substring(0,s)+".png";
               }
               ImageAttribute attr = new ImageAttribute(dir,fname);
               attributes.add(attr);
            }
            // String[] filenames={location+"background\\1.png ",location+"body\\1.png",location+"cloth\\1.png",location+"top\\1.png"};
            String key =JSONObject.toJSONString(attributes);
            if(localMap.containsKey(key)){
                continue;
            }

            String temp =getName();
            String outputname = outputDir+(temp)+".png";
            boolean make = makeOneImage(fileNames,outputname);
            if(!make){
                continue;
            }
            localMap.put(key,temp+".png");
            System.out.println("第"+i+"图片success："+fileNames.toString());
           i++;
           if(i>=total){
               System.out.println("finish----------------");
               break;
           }
        }
        LocalFileConvertObjectUtil.setFileObject(localMap,historyDir);
    }

    static Set<String> zimu = new HashSet<>();
    static char cha[]={'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
    public static String getName(){
        String temp ="";
        for(int i=0;i<10;i++)
        {
            int index=(int)(Math.random()*(cha.length));
            temp = temp+cha[index];
        }
        if(zimu.contains(temp)){
            temp = getName();
        }
        zimu.add(temp);
        return temp;
    }
    public static void run() throws IOException {
        Map<String,Object> localMap = LocalFileConvertObjectUtil.getFileObject(Map.class,historyDir);

        File root = new File(outputDir+"");
        int maxoutput = root.listFiles().length;
        for(int i=0;i<total;i++){
            List<File> fileNames = new ArrayList<>();
            List<ImageAttribute> attributes = new ArrayList<>();
            for(String dir:attrDirs){
                File file = new File(inputDir+dir);
                File[] filelist = file.listFiles();
                int length = filelist.length;
                Random r = new Random();
                int rand = r.nextInt(length);
                fileNames.add(filelist[rand]);
                String dirName = filelist[rand].getName();
                int p = dirName.indexOf(".png");
                ImageAttribute attr = new ImageAttribute(dir,dirName.substring(0,p));
                attributes.add(attr);
            }
           // String[] filenames={location+"background\\1.png ",location+"body\\1.png",location+"cloth\\1.png",location+"top\\1.png"};
            String key =JSONObject.toJSONString(attributes);
            if(localMap.containsKey(key)){
                i--;
                System.out.println("第"+i+"图片重复："+fileNames.toString());
                continue;
            }
            String outputname = outputDir+(maxoutput+i)+".png";
            boolean make = makeOneImage(fileNames,outputname);
            if(!make){
                i--;
                System.out.println("第"+i+"图片创建失败："+fileNames.toString());
                continue;
            }
            localMap.put(key,(maxoutput+i)+".png");
            System.out.println("第"+i+"图片success："+fileNames.toString());
        }
        LocalFileConvertObjectUtil.setFileObject(localMap,historyDir);
    }


    public static boolean makeOneImage(List<File> files,String inputFileName){
        try {
            if(files==null || files.size()==0){
                return false;
            }
            File first = files.get(0);
            BufferedImage ifirst = ImageIO.read(first);
            Graphics graphics = ifirst.getGraphics();
            for(int i=1;i<files.size();i++){
                File next = files.get(i);
                BufferedImage inext = ImageIO.read(next);
                graphics.drawImage(inext,0,0,null);
            }
            File outFile = new File(inputFileName);
            ImageIO.write(ifirst, "png", outFile);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }



}
