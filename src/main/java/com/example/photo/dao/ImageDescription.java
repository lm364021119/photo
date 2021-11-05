package com.example.photo.dao;

import java.util.ArrayList;
import java.util.List;

public class ImageDescription {
    String description;
    List<ImageAttribute> attributes;
    String image;

    public ImageDescription(String description, List<ImageAttribute> attributes, String image) {
        this.description = description;
        this.attributes = attributes;
        this.image = image;
    }

    public void addAttributes(ImageAttribute attr){
        if(attributes==null){
            attributes = new ArrayList<ImageAttribute>();
        }
        attributes.add(attr);
    }

    public List<ImageAttribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<ImageAttribute> attributes) {
        this.attributes = attributes;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
