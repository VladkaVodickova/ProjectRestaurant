package com.engeto.restaurant;

import java.math.BigDecimal;
import java.util.*;

public class RestaurantManager {
    private final List<Order> orderList;

    public RestaurantManager(List<Order> orderList) {
        this.orderList = orderList;
    }

    public int getNumberOfUnfinishedOrders() {
        int unfinishedCount = 0;
        for (Order order : orderList) {
            if (!order.isFinished()) {
                unfinishedCount++;
            }
        }
        return unfinishedCount;
    }
    public List<Order> sortByWaiter() {
        orderList.sort(Comparator.comparing(Order::getNameOfWaiter));
        return orderList;
    }

    public List<Order> sortByOrderTime() {
        orderList.sort(Comparator.comparing(Order::getOrderedTime));
        return orderList;
    }


    public Map<String, BigDecimal> calculateTotalPricePerWaiter() {
        Map<String, BigDecimal> totalPricePerWaiter = new HashMap<>();

        for (Order order : orderList) {
            String waiter = order.getNameOfWaiter();
            BigDecimal totalPrice = totalPricePerWaiter.getOrDefault(waiter, BigDecimal.ZERO);
            totalPrice = totalPrice.add(order.getPrice());
            totalPricePerWaiter.put(waiter, totalPrice);
        }

        return totalPricePerWaiter;
    }

}
