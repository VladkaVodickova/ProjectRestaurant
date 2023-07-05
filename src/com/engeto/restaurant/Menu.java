package com.engeto.restaurant;

import java.util.List;

public class Menu {
    private List<Menu> menus;

    public void addMenu (Menu menu, RecipeList recipeList) throws OrderException{
        if (menus.size() < recipeList.getSize()) {
            menus.add(menu);
        } else {
            throw new OrderException("Menu is too big.");
        }
    }

    public void removeMenu (Menu menu){
        menus.remove(menu);
    }

    public void clearMenu (Menu menu){
        menus.clear();
    }

    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }
}
