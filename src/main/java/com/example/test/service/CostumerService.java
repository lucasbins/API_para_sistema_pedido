package com.example.test.service;

import com.example.test.model.Costumer;
import com.example.test.repository.CostumerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CostumerService {

    @Autowired
    CostumerRepository repository;

    public void saveCostumer(Costumer costumer){
        this.repository.save(costumer);
    }

    public Optional<Costumer> findById(String id){
        return this.repository.findById(id);
    }

    public void deleteCostumer(Costumer costumer){
        this.repository.delete(costumer);
    }
}
