package com.downstreams.service;

import com.downstreams.model.Order;
import com.downstreams.model.Orders;

import java.util.Optional;

public interface IOrderBookService {

    Orders findAll();
    Optional<Order> findById(Long id);
    Order save(Order order);
    void deleteById(Long id);
    boolean exists(Order order);
}
