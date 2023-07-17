package com.engeto.restaurant;

import java.io.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Table {
    private int tableNumber;
    private List<Order> orderList;

    public Table(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    public Table(int tableNumber, List<Order> orderList) {
        this.tableNumber = tableNumber;
        this.orderList = orderList;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }

    public void setAndSaveTableNote (int tableNumber, String note) throws IOException {
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
