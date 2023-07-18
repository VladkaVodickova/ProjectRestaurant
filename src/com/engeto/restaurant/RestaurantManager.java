package com.engeto.restaurant;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
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


    public Map<String, BigDecimal> calculateTotalPricePerWaiter(List<Order> orderList) {
        Map<String, BigDecimal> totalPricePerWaiter = new HashMap<>();

        for (Order order : orderList) {
            String waiter = order.getNameOfWaiter();
            BigDecimal totalPrice = totalPricePerWaiter.getOrDefault(waiter, BigDecimal.ZERO);
            totalPrice = totalPrice.add(order.getPrice(orderList));
            totalPricePerWaiter.put(waiter, totalPrice);
        }

        return totalPricePerWaiter;
    }

    public int calculateAverageProcessingTime (LocalDate startDate, LocalDate endDate) {
        Duration totalProcessingTime = Duration.ZERO;
        int orderCount = 0;

        for (Order order : orderList) {
            LocalDate orderDate = order.getOrderDate();
            LocalTime orderedTime = order.getOrderedTime();
            LocalTime fulfillmentTime = order.getFulfilmentTime();

            if (orderDate.isAfter(startDate) && orderDate.isBefore(endDate.plusDays(1))) {
                Duration processingTime = order.getProcessingTime();
                totalProcessingTime = totalProcessingTime.plus(processingTime);
                orderCount++;
            }
        }
        if (orderCount > 0) {
            long averageProcessingTimeInMinutes = totalProcessingTime.toMinutes() / orderCount;
            return (int) averageProcessingTimeInMinutes;
        } else {
            return 0;
        }
    }
    public String getOrdersForTable (Table table, List<Order> orderList){
        return table.getOrderListing(orderList);
    }

    public String getListOfTodayOrderedMeals(List<Order> orderList){
        return Order.getOrderedMealNames(orderList);
    }

}
