package com.engeto.restaurant;

import java.io.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Order {
    private int tableNumber;
    private LocalTime  orderedTime;
    private List<Order> orderList;
    private String nameOfWaiter;
    private LocalTime fulfilmentTime;
    private Menu menu;

    public Order(int tableNumber, LocalTime  orderedTime, List<Order> orderList, String nameOfWaiter, LocalTime  fulfilmentTime) {
        this.tableNumber = tableNumber;
        this.orderedTime = orderedTime;
        this.orderList = orderList;
        this.nameOfWaiter = nameOfWaiter;
        this.fulfilmentTime = fulfilmentTime;
    }

    public Order(int tableNumber, LocalTime  orderedTime, List<Order> orderList, String nameOfWaiter) {
        this.tableNumber = tableNumber;
        this.orderedTime = orderedTime;
        this.orderList = orderList;
        this.nameOfWaiter = nameOfWaiter;
    }

    public int getTableNumber() {
        return tableNumber;
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

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
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

    public String getOrderListing (){
        StringBuilder orderListing = new StringBuilder();
        orderListing.append("** Objednávky pro stůl č. ").append(tableNumber).append(" **").append("\n****");
        for (Order order:orderList){
            orderListing.append("\n").append(orderList.indexOf(order)).append(". ").append(menu.getItemName()).append(" ").append(menu.getNumberofItems()).append("x (").append(menu.getItemPrice().multiply(BigDecimal.valueOf(menu.getNumberofItems()))).append(" Kč):\t").append(order.getOrderedTime()).append("-").append(order.getFulfilmentTime()).append("\tčíšník č. ").append(order.getNameOfWaiter()).append("\n");
        }
        orderListing.append("\n******");
        return orderListing.toString();
    }

    public void setAndSaveTableNote (int tableNumber, String note) throws IOException{
        String filename = "table_notes.txt";
        Set<String> tableNotes = new HashSet<>();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write(tableNumber + ": " + note);
            writer.newLine();
        } catch (IOException e) {
            throw new IOException("Error writing to file: " + filename, e);
        }
    }

    public String getTableNote (int tableNumber) throws IOException {
        String filename = "table_notes.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith(tableNumber + ": ")) {
                    return line.substring(line.indexOf(": ") + 2);
                }
            }
        } catch (IOException e) {
            throw new IOException("Error reading from file: " + filename, e);
        }
        return "";
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

}
