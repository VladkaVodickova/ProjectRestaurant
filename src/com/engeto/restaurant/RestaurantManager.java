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

    public String getDescriptionOfList(List<Order> orderList){
        StringBuilder descriptionOfList = new StringBuilder();
        for (Order order: orderList){
            descriptionOfList.append("Table: ").append(order.getTable().getTableNumber()).append("\t\t")
                    .append(String.format("%-40s", "Order: " + order.getRecipe().getItemName())).append("Quantity: ")
                    .append(order.getQuantityOfItems()).append("\t\t").append("Ordered Time: ").append(order.getOrderedTime())
                    .append("\t\t").append("Ordered by waiter: ").append(order.getNameOfWaiter()).append("\t\t")
                    .append(String.format("%-40s", "Order finished in: " + order.getFulfilmentTime())).append("Price: ")
                    .append(order.getPricePerOrder()).append(" Kè\n");
        }
        return descriptionOfList.toString();
    }

    public Map<String, BigDecimal> calculateTotalPricePerWaiter(List<Order> orderList) {
        Map<String, BigDecimal> totalPricePerWaiter = new HashMap<>();
        Map<String, Integer> orderCountPerWaiter = new HashMap<>();
        for (Order order : orderList) {
            String waiter = order.getNameOfWaiter();
            BigDecimal totalPrice = totalPricePerWaiter.getOrDefault(waiter, BigDecimal.ZERO);
            totalPrice = totalPrice.add(order.getPricePerOrder());
            totalPricePerWaiter.put(waiter, totalPrice);
            int orderCount = orderCountPerWaiter.getOrDefault(waiter, 0);
            orderCount++;
            orderCountPerWaiter.put(waiter, orderCount);
        }

        Map<String, BigDecimal> result = new HashMap<>();
        for (String waiter : totalPricePerWaiter.keySet()) {
            BigDecimal totalPrice = totalPricePerWaiter.get(waiter);
            int orderCount = orderCountPerWaiter.getOrDefault(waiter, 0);
            result.put(waiter + " (Number of orders: " + orderCount + ") ", totalPrice);
        }
        return result;
    }

    public long calculateAverageProcessingTime (LocalDate startDate, LocalDate endDate) {
        Duration totalProcessingTime = Duration.ZERO;
        int orderCount = 0;
        for (Order order : orderList) {
            LocalDate orderDate = order.getOrderDate();

            if ((orderDate.isEqual(startDate) || orderDate.isAfter(startDate)) &&
                    (orderDate.isEqual(endDate) || orderDate.isBefore(endDate.plusDays(1)))) {
                Optional<Duration> processingTimeOpt = order.getProcessingTime();
                if (processingTimeOpt.isPresent()) {
                    Duration processingTime = processingTimeOpt.get();
                    totalProcessingTime = totalProcessingTime.plus(processingTime);
                    orderCount++;
                }
            }
        }
        if (orderCount > 0) {
            return (totalProcessingTime.toMinutes() / orderCount);
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
