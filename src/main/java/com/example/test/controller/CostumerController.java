package com.example.test.controller;

import com.example.test.dto.CostumerDTO;
import com.example.test.model.Costumer;
import com.example.test.service.CostumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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

    @GetMapping("/{id}")
    public ResponseEntity<Costumer> findById (@RequestParam String id){
        if(id == null) return ResponseEntity.badRequest().build();

        Optional<Costumer> costumer = this.service.findById(id);

        return costumer.map(value -> ResponseEntity.status(HttpStatus.OK).body(value)).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Costumer> editCostumer(@RequestParam String id, @RequestBody CostumerDTO data){
        if(id == null) return ResponseEntity.badRequest().build();

        Optional<Costumer> costumer = this.service.findById(id);

        if(costumer.isPresent()){
            Costumer newCostumer = costumer.get();

            newCostumer.setEmail(data.email());
            newCostumer.setName(data.name());
            newCostumer.setPhone(data.phone());

            this.service.saveCostumer(newCostumer);

            return ResponseEntity.status(HttpStatus.OK).body(newCostumer);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCostumer(@RequestParam String id){
        if(id == null) return ResponseEntity.badRequest().build();

        Optional<Costumer> costumer = this.service.findById(id);

        if(costumer.isPresent()){
            this.service.deleteCostumer(costumer.get());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
