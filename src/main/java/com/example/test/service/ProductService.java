package com.example.test.service;

import com.example.test.model.Product;
import com.example.test.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepository repository;

    public void saveProduct(Product product){
        this.repository.save(product);
    }

    public Optional<Product> findById(Long id){
        return this.repository.findById(id);
    }

    public List<Product> findAll(){
        return this.repository.findAll();
    }

    public void deleteProduct(Product product){
        this.repository.delete(product);
    }
}
