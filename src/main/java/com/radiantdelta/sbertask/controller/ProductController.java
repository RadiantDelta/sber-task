package com.radiantdelta.sbertask.controller;

import com.radiantdelta.sbertask.domain.Product;
import com.radiantdelta.sbertask.dto.ProductDTO;
import com.radiantdelta.sbertask.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping()
public class ProductController {
    @Autowired
    private ProductService productService;


    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts() {

        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable int id) {


        return ResponseEntity.ok(productService.findById(id));



    }

    @PostMapping
    public int createProduct(@RequestBody ProductDTO productDTO) {

        return productService.createProduct(productDTO);

    }



    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id) {

            Product product = productService.findById(id);

            productService.deleteProduct(ProductDTO.from(product));

            return ResponseEntity.ok().build();

    }

    @PutMapping("/{id}")
    public ResponseEntity<String> replaceProduct(@RequestBody Product newProduct, @PathVariable int id) {

            productService.replaceProduct(ProductDTO.from(newProduct), id);

            return ResponseEntity.ok().build();


    }






}
