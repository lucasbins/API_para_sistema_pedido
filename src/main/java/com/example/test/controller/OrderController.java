package com.example.test.controller;

import com.example.test.dto.OrderDTO;
import com.example.test.model.Costumer;
import com.example.test.model.Order;
import com.example.test.service.CostumerService;
import com.example.test.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("order")
public class OrderController {

    @Autowired
    OrderService service;

    @Autowired
    CostumerService costumerService;

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

    @PostMapping
    public ResponseEntity<Order> newOrder(@RequestBody OrderDTO data){
        if(data == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        Optional<Costumer> costumer = this.costumerService.findById(data.IdCostumer());

        if(costumer.isPresent()){
            Order newOrder = new Order(costumer.get(), data.amount());

            this.service.saveOrder(newOrder);

            return ResponseEntity.status(HttpStatus.CREATED).body(newOrder);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
