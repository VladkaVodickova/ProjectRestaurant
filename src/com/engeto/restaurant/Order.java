package com.engeto.restaurant;

import java.io.*;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Order{
    private Table table;
    private LocalDate orderDate;
    private LocalTime  orderedTime;
    private List<Order> orderList;
    private String nameOfWaiter;
    private LocalTime fulfilmentTime;
    private List<Recipe> recipeList;
    private Menu menu;

    public Order(Table table, LocalTime  orderedTime, List<Recipe> recipeList, String nameOfWaiter, LocalTime  fulfilmentTime) {
        this.table = table;
        this.orderedTime = orderedTime;
        this.recipeList = recipeList;
        this.nameOfWaiter = nameOfWaiter;
        this.fulfilmentTime = fulfilmentTime;
        setOrderDate();
    }

    public Order(Table table, LocalTime  orderedTime, List<Recipe> recipeList, String nameOfWaiter) {
        this.table = table;
        this.orderedTime = orderedTime;
        this.recipeList = recipeList;
        this.nameOfWaiter = nameOfWaiter;
        setOrderDate();
    }

    public Table getTable() {
        return table;
    }

    public List<Recipe> getRecipeList() {
        return recipeList;
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

    public BigDecimal getPrice() {
        BigDecimal totalPrice = BigDecimal.ZERO;
        for (Order order : orderList) {
            BigDecimal itemPrice = menu.getItemPrice();
            int quantity = menu.getNumberofItems();
            BigDecimal itemTotalPrice = itemPrice.multiply(BigDecimal.valueOf(quantity));
            totalPrice = totalPrice.add(itemTotalPrice);
        }
        return totalPrice;
    }

    public String getOrderListing(int tableNumber) {
        StringBuilder orderListing = new StringBuilder();
        orderListing.append("** Objednávky pro stůl č. ").append(tableNumber).append(" **").append("\n****");

        for (Order order : orderList) {
            if (order.getTableNumber() == tableNumber) {
                orderListing.append("\n").append(orderList.indexOf(order)).append(". ");
                List<Menu> menuList = order.getRecipeList();

                for (Menu menu : menuList) {
                    orderListing.append(menu.getItemName()).append(" ").append(menu.getNumberofItems()).append("x (")
                            .append(menu.getItemPrice().multiply(BigDecimal.valueOf(menu.getNumberofItems())))
                            .append(" Kč):\t").append(order.getOrderedTime()).append("-").append(order.getFulfilmentTime())
                            .append("\tčíšník č. ").append(order.getNameOfWaiter()).append("\n");
                }
            }
        }

        orderListing.append("\n******");
        return orderListing.toString();
    }



    public void saveOrder () throws IOException{
        String filename = "orders.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write("Table Number: " + tableNumber + "\nOrder Time: " +  orderedTime + "\nOrdered items: " +
                    orderList + "\nName of waiter: " + nameOfWaiter + "\nOrder fulfilled in: " + fulfilmentTime+ "\n");
            writer.newLine();
        } catch (IOException e) {
            throw new IOException("Error writing to file: " + filename, e);
        }
    }

    public void loadOrders(String filePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\n");

                int tableNumber = Integer.parseInt(parts[0]);
                String nameOfWaiter = parts[1];
                /*
                List<Recipe> recipeList = new ArrayList<>();
                String[] recipeId = parts[2].split(";");
                for (Recipe recipe : recipeList) {
                    Recipe recipe = findRecipeById(recipeId); // Implement the logic to find the recipe by ID
                    if (recipe != null) {
                        recipeList.add(recipe);
                    } else {
                        throw new OrderException("Recipe not found for ID: " + recipeId);
                    }
                }*/
                LocalTime  orderedTime = LocalTime .parse(parts[3]);
                LocalTime  fulfilmentTime = LocalTime .parse(parts[4]);

                Order order = new Order(tableNumber, orderedTime, new ArrayList<>(), nameOfWaiter, fulfilmentTime);
                orderList.add(order);
            }
        } catch (FileNotFoundException e) {
            throw new IOException("File not found: " + filePath);
        } catch (IOException e) {
            throw new IOException("Error reading file: " + filePath, e);
        }
    }

    public Duration getProcessingTime(){
        return Duration.between(orderedTime,fulfilmentTime);
    }
//nemám nejmenšího tucha jak to udělat
/*
    public List<String> getOrderedMealNames() {
        List<String> orderedMealNames = new ArrayList<>();

        for (Order order : orderList) {
            // Assuming the menu object is accessible from the Order class
            String mealName = order.getMenu().getItemName();
            orderedMealNames.add(mealName);
        }

        return orderedMealNames;
    }
*/
}
