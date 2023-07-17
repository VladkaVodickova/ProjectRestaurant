package com.engeto.restaurant;

import java.io.*;
import java.math.BigDecimal;
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

    public String getOrderListing(int tableNumber) {
        StringBuilder orderListing = new StringBuilder();
        orderListing.append("** Objednávky pro stůl č. ").append(tableNumber).append(" **").append("\n****");

        for (Order order : orderList) {
            if (tableNumber == order.getTable().getTableNumber()) {
                orderListing.append("\n").append(orderList.indexOf(order)).append(". ");
                    orderListing.append(order.getRecipe().getItemName()).append(" ").append(order.getQuantityOfItems()).append("x (")
                            .append(order.getRecipe().getItemPrice().multiply(BigDecimal.valueOf(order.getQuantityOfItems())))
                            .append(" Kč):\t").append(order.getOrderedTime()).append("-").append(order.getFulfilmentTime())
                            .append("\tčíšník č. ").append(order.getNameOfWaiter()).append("\n");
            }
        }
        orderListing.append("\n******");
        return orderListing.toString();
    }
}
