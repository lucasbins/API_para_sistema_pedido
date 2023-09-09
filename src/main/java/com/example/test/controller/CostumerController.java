package com.example.test.controller;

import com.example.test.dto.CostumerDTO;
import com.example.test.model.Costumer;
import com.example.test.service.CostumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("costumer")
public class CostumerController {

    @Autowired
    CostumerService service;

    @PostMapping
    public ResponseEntity<Costumer> newCostumer (@RequestBody CostumerDTO data){
        if(data == null) return ResponseEntity.badRequest().build();

        Costumer costumer = new Costumer(data.name(), data.phone(), data.email());

        this.service.saveCostumer(costumer);

        return ResponseEntity.status(HttpStatus.CREATED).body(costumer);
    }
}
