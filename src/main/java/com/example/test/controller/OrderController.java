package com.example.test.controller;

import com.example.test.model.Order;
import com.example.test.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("order")
public class OrderController {

    @Autowired
    OrderService service;

    @GetMapping
    public List<Order> findAll() {
        return this.service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> findById (@RequestParam Long id){
        if(id== null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        Optional<Order> order = this.service.findById(id);

        return order.map(value -> ResponseEntity.status(HttpStatus.OK).body(value)).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }


}
