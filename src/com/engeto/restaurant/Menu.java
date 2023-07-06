package com.engeto.restaurant;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public class Menu {
    private String itemName;
    private BigDecimal itemPrice;
    private int numberofItems;
    private List<Menu> menuList;


    public Menu(String itemName, int numberofItems, BigDecimal itemPrice) {
        this.itemName = itemName;
        this.numberofItems = numberofItems;
        this.itemPrice = itemPrice;
    }

    public String getItemName() {
        return itemName;
    }

    public BigDecimal getItemPrice() {
        return itemPrice;
    }

    public int getNumberofItems() {
        return numberofItems;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setItemPrice(BigDecimal itemPrice) {
        this.itemPrice = itemPrice;
    }

    public void setNumberofItems(int numberofItems) {
        this.numberofItems = numberofItems;
    }

    public void addMenu (Menu menu, RecipeList recipeList) throws OrderException{
        if (menuList.size() < recipeList.getSize()) {
            menuList.add(menu);
        } else {
            throw new OrderException("Menu is too big.");
        }
    }

    public void removeMenu (Menu menu){
        menuList.remove(menu);
    }

    public void clearMenu (Menu menu){
        menuList.clear();
    }

    public List<Menu> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<Menu> menuList) {
        this.menuList = menuList;
    }

    public String getDescription (){
        StringBuilder description = new StringBuilder();
        for (Menu menu: menuList){
            description.append(getMenuList()).append("\n");
        }
        return description.toString();
    }

    public void saveMenu () throws IOException {
        String filename = "menu.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
                writer.write(getDescription() +  "\n");
                writer.newLine();
        } catch (IOException e) {
            throw new IOException("Error writing to file: " + filename, e);
        }
    }
}
