package com.example.photo.imagesutil;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class MainTest {
    static String  outputDir = "D:\\images\\output\\";
    static String  inputDir = "D:\\images\\sucai\\";
    static String[] attrDirs = {"Background","Clothing","Ears","Hat","Neck","Eyes","Style"};
    static int total =4000;
    public static void main(String[] args) {
        String[][] data = getCountData();
        Random r = new Random();
        List<Map<String, String>> list = new ArrayList<>();
        for (int m = 0; m < total; m++) {
            Map<String, String> map = new HashMap<>();
            for (int i = 0; i < attrDirs.length; i++) {
                String dir = attrDirs[i];
                int rank = r.nextInt(total);
                String value = data[i][rank];
                if(value !=null && !"".equals(value)){
                    map.put(dir, value);
                }
            }
            list.add(map);
        }
        try {
            LocalFileConvertObjectUtil.setFileObject(list,"D:\\images\\list.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("finsh");
        //int rand = r.nextInt(length);
    }

    private static String[][] getCountData() {
        getCountConfig();
        String[][] data = new String[attrDirs.length][total];
        for(int i=0; i<attrDirs.length;i++) {
            String dir = attrDirs[i];
            Map<String,Integer> map =new HashMap<String,Integer>();
            try {
                map =  LocalFileConvertObjectUtil.getFileObject(Map.class,"D:\\images\\"+dir+".txt");
            } catch (IOException e) {
                e.printStackTrace();
            }

            Set<String> keys =map.keySet();
            int sum =0;
            for(String key :keys){
                int value = map.get(key);
                int init=value *total/100;
                if(dir.equals("Style")){
                    init = value *total/1000;
                }
                if(init==0){
                    init =1;
                }
                while (init !=0){
                    data[i][sum]=key;
                    init--;
                    sum++;
                    if(sum>total){
                        System.out.println(dir+"超过"+total+"张");
                        break;
                    }
                }
            }
        }
        return data;
    }

    public static void getCountConfig() {
        for(String dir:attrDirs){
            File rootfile = new File(inputDir+dir);
            File[] filelist = rootfile.listFiles();
            Map<String,Integer> map = new HashMap<>();
            for(File file :filelist){
                String filename = file.getName();
                try{
                int p = filename.indexOf("-");
                int endp = filename.indexOf(".");
                String key = filename.substring(0, p);
                int value = Integer.parseInt(filename.substring(p+1,endp));
                map.put(filename,value);
                }catch (Exception e){
                    System.out.println(file);
                        throw e;
                }
            }
            try {
                LocalFileConvertObjectUtil.setFileObject(map,"D:\\images\\"+dir+".txt");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
