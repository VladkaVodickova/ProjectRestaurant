package com.engeto.restaurant;

import java.awt.*;
import java.io.*;
import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Recipe {
    private String title;
    private BigDecimal price;
    private int preparationTime;
    private List<URL> imageURLS; // should contain blank if no photo present
    private List<Recipe> recipeList; //Warning:(16, 26) Contents of collection 'recipeList' are updated, but never queried
    private Category category;

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

    public String getDescription(){
        return title + "\t" + price + "Kč";
    }

    public void saveRecipe () throws IOException {
        String filename = "recipe.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write("Title: " + title + "\nPrice: " +  price + "\nPreparation time: " +
                    preparationTime + "\nImages: " + imageURLS + "\n");
            writer.newLine();
        } catch (IOException e) {
            throw new IOException("Error writing to file: " + filename, e);
        }
    }

    public void loadRecipe (String filePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\n");

                String title = parts[0];
                String priceString = parts[1];
                BigDecimal price = new BigDecimal(priceString);
                int preparationTime = Integer.parseInt(parts[0]);
                LocalDate fulfilmentTime = LocalDate.parse(parts[4]);

                Recipe recipe = new Recipe(title, price, preparationTime, new ArrayList<>());
                recipeList.add(recipe);
            }
        } catch (FileNotFoundException e) {
            throw new IOException("File not found: " + filePath);
        } catch (IOException e) {
            throw new IOException("Error reading file: " + filePath, e);
        }
    }
}
