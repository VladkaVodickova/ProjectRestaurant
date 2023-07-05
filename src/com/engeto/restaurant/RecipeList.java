package com.engeto.restaurant;

import java.util.List;

public class RecipeList {
    private List<Recipe> recipeList;

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

}
