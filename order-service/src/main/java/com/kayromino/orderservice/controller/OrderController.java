package com.kayromino.orderservice.controller;

import com.kayromino.orderservice.entity.Order;
import com.kayromino.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Order> addOrder(@RequestBody Order order) {
        try {
            Optional<Order> orderOptional = orderService.addOrder(order);
            return orderOptional.isPresent() ?
                    new ResponseEntity(orderOptional.get(), HttpStatus.OK) :
                    new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<Order> updateOrder(@RequestBody Order order) {
        try {
            Optional<Order> orderOptional = orderService.updateOrder(order);
            return orderOptional.isPresent() ?
                    new ResponseEntity(orderOptional.get(), HttpStatus.OK) :
                    new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Optional<List<Order>>> getAllOrders() {
        try {
            Optional<List<Order>> optionalOrderList = orderService.getAllOrders();
            return optionalOrderList.isPresent() ? new ResponseEntity(optionalOrderList.get(), HttpStatus.OK) : new ResponseEntity(HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Optional<Order>> getOrderById(@PathVariable Long id) {
        try {
            Optional<Order> orderOptional = orderService.getOrderById(id);
            return orderOptional.isPresent() ?
                    new ResponseEntity(orderOptional.get(), HttpStatus.OK) :
                    new ResponseEntity(HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/fetch-order/{fromDate}/{toDate}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Optional<Order>> getOrdersBetweenTwoDates(@PathVariable("fromDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromDate,
                                                                    @PathVariable("toDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate toDate) {
        try {
            Optional<List<Order>> optionalOrderList = orderService.getOrdersBetweenTwoDates(fromDate, toDate);
            return optionalOrderList.isPresent() ? new ResponseEntity(optionalOrderList.get(), HttpStatus.OK) : new ResponseEntity(HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/total-price/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Double> getTotalOrderPrice(@PathVariable Long id) {
        try {
            Double totalOrderPrice = orderService.getTotalOrderPrice(id);
            return new ResponseEntity(totalOrderPrice, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/total-products/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Integer> getNumberOfProductsByOrderId(@PathVariable Long id) {
        try {
            Integer totalNumOfProducts = orderService.getNumberOfProductsByOrderId(id);
            return new ResponseEntity(totalNumOfProducts, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
