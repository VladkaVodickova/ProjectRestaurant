package com.engeto.restaurant;

import java.io.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Order {
    private int tableNumber;
    private LocalDate orderedTime;
    private List<Order> orderList;
    private String nameOfWaiter;
    private LocalDate fulfilmentTime;

    public Order(int tableNumber, LocalDate orderedTime, List<Order> orderList, String nameOfWaiter, LocalDate fulfilmentTime) {
        this.tableNumber = tableNumber;
        this.orderedTime = orderedTime;
        this.orderList = orderList;
        this.nameOfWaiter = nameOfWaiter;
        this.fulfilmentTime = fulfilmentTime;
    }

    public Order(int tableNumber, LocalDate orderedTime, List<Order> orderList, String nameOfWaiter) {
        this.tableNumber = tableNumber;
        this.orderedTime = orderedTime;
        this.orderList = orderList;
        this.nameOfWaiter = nameOfWaiter;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public LocalDate getOrderedTime() {
        return orderedTime;
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public String getNameOfWaiter() {
        return nameOfWaiter;
    }

    public LocalDate getFulfilmentTime() {
        return fulfilmentTime;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    public void setOrderedTime(LocalDate orderedTime) {
        this.orderedTime = orderedTime;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }

    public void setNameOfWaiter(String nameOfWaiter) {
        this.nameOfWaiter = nameOfWaiter;
    }

    public void setFulfilmentTime(LocalDate fulfilmentTime) {
        this.fulfilmentTime = fulfilmentTime;
    }

    public void addOrder (Order order){
        orderList.add(order);
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


}
