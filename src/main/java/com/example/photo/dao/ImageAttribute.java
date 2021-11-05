package com.example.photo.dao;

public class ImageAttribute {
    String trait_type;
    String value;

    public ImageAttribute(String trait_type, String value) {
        this.trait_type = trait_type;
        this.value = value;
    }

    public String getTrait_type() {
        return trait_type;
    }

    public void setTrait_type(String trait_type) {
        this.trait_type = trait_type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
