package com.engeto.restaurant;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Menu{
    private final List<Recipe> recipeList;
    private final List<Recipe> menuItems;

    public Menu(List<Recipe> recipeList) {
        this.recipeList = recipeList;
        this.menuItems = new ArrayList<>();
    }

    public void addMenu (Recipe recipe) throws OrderException{
        if (menuItems.size() < recipeList.size()) {
            menuItems.add(recipe);
        } else {
            throw new OrderException("Menu is too big.");
        }
    }

    public void removeMenu (Recipe recipe){
        menuItems.remove(recipe);
    }

    public void clearMenu (Recipe recipe){
        menuItems.clear();
    }

    public List<Recipe> getMenu() {
        return menuItems;
    }

    public String getMenuDescription (){
        StringBuilder description = new StringBuilder();
        for (Recipe recipe: recipeList){
            description.append(recipe.getDescription()).append("\n");
        }
        return description.toString();
    }
}
