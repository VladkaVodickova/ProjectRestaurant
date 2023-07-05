package com.engeto.restaurant;

import java.awt.*;
import java.math.BigDecimal;
import java.net.URL;
import java.util.List;

public class Recipe {
    private String title;
    private BigDecimal price;
    private int preparationTime;
    private List<URL> imageURLS;

    public Recipe(String title, BigDecimal price, int preparationTime, List<URL> imageURLS) {
        this.title = title;
        this.price = price;
        this.preparationTime = preparationTime;
        this.imageURLS = imageURLS;
    }

    public String getTitle() {
        return title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getPreparationTime() {
        return preparationTime;
    }

    public List<URL> getImageURLS() {
        return imageURLS;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setPreparationTime(int preparationTime) {
        this.preparationTime = preparationTime;
    }

    public void setImageURLS(List<URL> imageURLS) {
        this.imageURLS = imageURLS;
    }

    public void addImage (URL image){
        imageURLS.add(image);
    }

    public void removeImage (URL image) throws OrderException{
        if (imageURLS.size()>1) {
            imageURLS.remove(image);
        } else {
            throw new OrderException("At least one image must be present.");
        }
    }
}
