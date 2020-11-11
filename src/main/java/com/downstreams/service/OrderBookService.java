package com.downstreams.service;

import com.downstreams.model.Order;
import com.downstreams.model.Orders;
import com.downstreams.repository.OrderBookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderBookService implements IOrderBookService {

    @Autowired
    OrderBookingRepository repository;

    @Override
    public Orders findAll() {
        List<Order> orders = (List<Order>)repository.findAll();
        Orders ordersBook = new Orders();
        ordersBook.setOrders(orders);
        return ordersBook;
    }

    @Override
    public Optional<Order> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Order save(Order order) {
        return repository.save(order);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public boolean exists(Order order) {
        return findById(Long.valueOf(order.getId().toString())).isEmpty();
    }
}
