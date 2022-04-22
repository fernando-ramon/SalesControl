package com.spring.bestpractices.controller;

import com.spring.bestpractices.domain.Product;
import com.spring.bestpractices.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/product")
    public ResponseEntity<List<Product>> getAll() {
        return new ResponseEntity<>(productService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Product> get(@PathVariable Long id) {
        return new ResponseEntity<>(productService.get(id), HttpStatus.OK);
    }

    @PostMapping("/product")
    public ResponseEntity<Product> insert(@Valid @RequestBody Product product) {
        return new ResponseEntity<>(productService.insert(product), HttpStatus.CREATED);
    }

    @PutMapping("/product")
    public ResponseEntity<Product> update(@Valid @RequestBody Product product) {
        return new ResponseEntity<>(productService.update(product), HttpStatus.OK);
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity update(@PathVariable Long id) {
        productService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
