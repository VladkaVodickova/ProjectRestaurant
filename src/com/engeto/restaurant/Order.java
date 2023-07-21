package com.engeto.restaurant;

import java.io.*;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class Order{
    private Table table;
    private LocalDate orderDate;
    private LocalTime  orderedTime;
    private List<Order> orderList;
    private String nameOfWaiter;
    private LocalTime fulfilmentTime;
    private Recipe recipe;
    private int quantityOfItems;
    private Menu menu;

    public Order(Table table, Menu menu, Recipe recipe, int quantityOfItems, LocalTime  orderedTime, String nameOfWaiter, LocalTime  fulfilmentTime) throws OrderException {
        this.table = table;
        this.orderedTime = orderedTime;
        this.menu = menu;
        setRecipe(recipe);
        this.nameOfWaiter = nameOfWaiter;
        this.fulfilmentTime = fulfilmentTime;
        setOrderDate();
        this.quantityOfItems = quantityOfItems;
    }

    public Order(Table table, Menu menu, Recipe recipe, int quantityOfItems, LocalTime  orderedTime, String nameOfWaiter) throws OrderException {
        this.table = table;
        this.orderedTime = orderedTime;
        this.menu = menu;
        setRecipe(recipe);
        this.nameOfWaiter = nameOfWaiter;
        setOrderDate();
        this.quantityOfItems = quantityOfItems;
    }
    public Order(Table table, Recipe recipe, int quantityOfItems, LocalTime  orderedTime, String nameOfWaiter, LocalTime  fulfilmentTime) throws OrderException {
        this.table = table;
        this.orderedTime = orderedTime;
        this.recipe = recipe;
        this.nameOfWaiter = nameOfWaiter;
        this.fulfilmentTime = fulfilmentTime;
        setOrderDate();
        this.quantityOfItems = quantityOfItems;
    }

    public Table getTable() {
        return table;
    }

    public int getQuantityOfItems() {
        return quantityOfItems;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public LocalTime  getOrderedTime() {
        return orderedTime;
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public String getNameOfWaiter() {
        return nameOfWaiter;
    }

    public LocalTime  getFulfilmentTime() {
        return fulfilmentTime;
    }

    public void setOrderDate() {
        this.orderDate = LocalDate.now();
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public void setRecipe(Recipe recipe) throws OrderException {
        if (!menu.isRecipeInMenu(recipe)) {
            throw new OrderException ("Recipe is not in the menu");
        }
        this.recipe = recipe;
    }

    public void setQuantityOfItems(int quantityOfItems) {
        this.quantityOfItems = quantityOfItems;
    }

    public void setOrderedTime(LocalTime  orderedTime) {
        this.orderedTime = orderedTime;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }

    public void setNameOfWaiter(String nameOfWaiter) {
        this.nameOfWaiter = nameOfWaiter;
    }

    public void setFulfilmentTime(LocalTime  fulfilmentTime) {
        this.fulfilmentTime = fulfilmentTime;
    }

    public void addOrder (Order order){
        orderList.add(order);
    }



    public boolean isFinished() {
        return fulfilmentTime != null;
    }

    public BigDecimal getPricePerOrder() {
        return recipe.getItemPrice().multiply(BigDecimal.valueOf(quantityOfItems));
    }

    public Optional<Duration> getProcessingTime() {
        if (fulfilmentTime != null) {
            return Optional.of(Duration.between(orderedTime, fulfilmentTime));
        }
        return Optional.empty();
    }

    public static String getOrderedMealNames(List<Order> orderList) {
        Set<String> uniqueMealNames = new HashSet<>();
        StringBuilder orderedMealNames = new StringBuilder();

        for (Order order : orderList) {
            if (order.getOrderDate().equals(LocalDate.now())) {
                String mealName = order.getRecipe().getItemName();

                if (!uniqueMealNames.contains(mealName)) {
                    orderedMealNames.append(mealName).append("\t");
                    uniqueMealNames.add(mealName);
                }
            }
        }
        return orderedMealNames.toString();
    }
}
