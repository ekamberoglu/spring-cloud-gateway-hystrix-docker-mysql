package com.kayromino.orderservice.service;

import com.kayromino.orderservice.entity.Order;
import com.kayromino.orderservice.entity.OrderDetail;
import com.kayromino.orderservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Optional<List<Order>> getAllOrders() {
        List<Order> orderList = orderRepository.findAll();
        return orderList != null ? Optional.of(orderList) : Optional.empty();
    }

    @Override
    public Optional<List<Order>> getOrdersBetweenTwoDates(LocalDate fromDate, LocalDate toDate) {
        return orderRepository.getOrdersBetweenTwoDates(fromDate, toDate);
    }

    @Override
    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    @Override
    public Optional<Order> updateOrder(Order order) {
        LocalDate now = LocalDate.now();
        order.setDateUpdated(now);
        order.getOrderDetailList().forEach(orderDetail -> orderDetail.setDateUpdated(now));
        order = orderRepository.save(order);
        return order != null ? Optional.of(order) : Optional.empty();
    }

    @Override
    public Optional<Order> addOrder(Order order) {
        LocalDate now = LocalDate.now();
        order.setDateCreated(now);
        order.getOrderDetailList().forEach(orderDetail -> orderDetail.setDateCreated(now));
        order = orderRepository.save(order);
        return order != null ? Optional.of(order) : Optional.empty();
    }

    @Override
    public Double getTotalOrderPrice(Long id) {
        Double totalAmount = 0.0;

        Optional<Order> orderOptional = orderRepository.findById(id);
        if (orderOptional.isPresent()) {
            Order order = orderOptional.get();
            List<OrderDetail> orderDetailList = order.getOrderDetailList();
            if (orderDetailList != null && orderDetailList.size() > 0) {
                totalAmount = orderDetailList.stream().mapToDouble(orderDetail -> orderDetail.getPrice() * orderDetail.getQuantity()).sum();
            }
        }

        return totalAmount;
    }

    @Override
    public int getNumberOfProductsByOrderId(Long id) {
        int numOfProduct = 0;

        Optional<Order> orderOptional = orderRepository.findById(id);
        if (orderOptional.isPresent()) {
            Order order = orderOptional.get();
            List<OrderDetail> orderDetailList = order.getOrderDetailList();
            if (orderDetailList != null && orderDetailList.size() > 0) {
                numOfProduct = orderDetailList.size();
            }
        }

        return numOfProduct;
    }
}
