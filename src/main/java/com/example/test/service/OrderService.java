package com.example.test.service;

import com.example.test.model.Order;
import com.example.test.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    OrderRepository repository;

    public void saveOrder(Order order){
        this.repository.save(order);
    }

    public void deleteOrder(Order order){
        this.repository.delete(order);
    }

    public Optional<Order> findById(Long id){
        return this.repository.findById(id);
    }

    public List<Order> findAll(){
        return this.repository.findAll();
    }
}

