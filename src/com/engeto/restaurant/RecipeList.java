package com.engeto.restaurant;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.List;

public class RecipeList extends Recipe{
    private List<Recipe> recipeList;

    public RecipeList(String title, BigDecimal price, int preparationTime, List<URL> imageURLS) {
        super(title, price, preparationTime, imageURLS);
    }

    public List<Recipe> getRecipeList() {
        return recipeList;
    }

    public void setRecipeList(List<Recipe> recipeList) {
        this.recipeList = recipeList;
    }

    public void addRecipe (Recipe recipe){
        recipeList.add(recipe);
    }

    public void removeRecipe (Recipe recipe){
        recipeList.remove(recipe);
    }

    public int getSize(){
        return recipeList.size();
    }

    public void updateRecipe (Recipe formerRecipe, Recipe newRecipe){
        int index = recipeList.indexOf(formerRecipe);
        if (index != -1){
            recipeList.set(index, newRecipe);
        }
    }

    public String getDescription (){
        StringBuilder description = new StringBuilder();
        for (Recipe recipe: recipeList){
            description.append(getRecipeList()).append("\n");
        }
        return description.toString();
    }
}
