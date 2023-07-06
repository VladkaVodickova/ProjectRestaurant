package com.engeto.restaurant;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class RecipeList {
    private List<Recipe> recipeList;

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

    public void saveRecipeList () throws IOException {
        String filename = "Recipe_list.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
                writer.write(getDescription() +  "\n");
                writer.newLine();
        } catch (IOException e) {
            throw new IOException("Error writing to file: " + filename, e);
        }
    }

}
