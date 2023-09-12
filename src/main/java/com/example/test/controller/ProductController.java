package com.example.test.controller;

import com.example.test.dto.ProductDTO;
import com.example.test.model.Product;
import com.example.test.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("product")
public class ProductController {

    @Autowired
    ProductService service;

    @GetMapping
    public List<Product> getAllProducts(){
        return this.service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> findById (@RequestParam Long id){
        if(id == null) return ResponseEntity.badRequest().build();

        Optional<Product> product = this.service.findById(id);

        return product.map(value -> ResponseEntity.status(HttpStatus.OK).body(value)).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    public ResponseEntity<Product> newProduct(@RequestBody ProductDTO data){
        if(data == null) return ResponseEntity.badRequest().build();

        Product newProduct = new Product(data.name(), data.priceInCents(), data.description());

        this.service.saveProduct(newProduct);

        return ResponseEntity.status(HttpStatus.CREATED).body(newProduct);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> editProduct(@RequestParam Long id, @RequestBody ProductDTO data){
        if(id == null) return ResponseEntity.badRequest().build();

        Optional<Product> product = this.service.findById(id);

        if(product.isPresent()){
            Product newProduct = product.get();

            newProduct.setName(newProduct.getName());
            newProduct.setDescription(newProduct.getDescription());
            newProduct.setPriceInCents(newProduct.getPriceInCents());

            this.service.saveProduct(newProduct);

            return ResponseEntity.status(HttpStatus.OK).body(newProduct);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@RequestParam Long id){
        if(id == null) return ResponseEntity.badRequest().build();

        Optional<Product> product = this.service.findById(id);

        if(product.isPresent()){
            this.service.deleteProduct(product.get());
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
