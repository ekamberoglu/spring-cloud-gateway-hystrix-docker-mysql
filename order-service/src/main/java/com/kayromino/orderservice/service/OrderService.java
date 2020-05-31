package com.kayromino.orderservice.service;

import com.kayromino.orderservice.entity.Order;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface OrderService {
    Optional<List<Order>> getAllOrders();
    Optional<List<Order>> getOrdersBetweenTwoDates(LocalDate fromDate, LocalDate toDate);
    Optional<Order> getOrderById(Long id);
    Optional<Order> updateOrder(Order order);
    Optional<Order> addOrder(Order order);
    Double getTotalOrderPrice(Long id);
    int getNumberOfProductsByOrderId(Long id);
}
