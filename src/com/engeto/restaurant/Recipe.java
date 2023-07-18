package com.engeto.restaurant;

import java.math.BigDecimal;
import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Recipe {
    private String title;
    private BigDecimal price;
    private int preparationTime;
    private List<URL> imageURLS;
    private List<Recipe> recipeList; //Warning:(16, 26) Contents of collection 'recipeList' are updated, but never queried
    private Category category;

    public Recipe(String title, BigDecimal price, int preparationTime, List<URL> imageURLS) {
        this.title = title;
        this.price = price;
        this.preparationTime = preparationTime;
        this.imageURLS = Objects.requireNonNullElse(imageURLS, Collections.emptyList());
    }

    public Recipe(String title, BigDecimal price, int preparationTime, List<URL> imageURLS, Category category) {
        this.title = title;
        this.price = price;
        this.preparationTime = preparationTime;
        this.imageURLS = Objects.requireNonNullElse(imageURLS, Collections.emptyList());
        this.category = category;
    }

    public Recipe(String title, BigDecimal price) {
        this.title = title;
        this.price = price;
    }

    public Category getCategory() {
        return category;
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

    public void setCategory(Category category) {
        this.category = category;
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

    public String getDescription(){
        return title + "\t" + price;
    }

    public String getItemName(){
        return title;
    }
    public BigDecimal getItemPrice(){
        return new BigDecimal(String.valueOf(price));
    }
}
