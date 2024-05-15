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

    /**
     * Retrieves all Products from the database
     * @return The list of Products retrieved from the database.
     */
    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts() {

        return ResponseEntity.ok(productService.getAllProducts());
    }

    /**
     * Retrieves  Product with specific id from the database
     * @param id id of Product to be retrieved
     * @return The Product with specific id from the database
     */
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable int id) {

        return ResponseEntity.ok(productService.findById(id));


    }

    /**
     *  Handles creation of new Product
     * @param productDTO the details of Product to be created
     * @return id of saved Product
     */
    @PostMapping
    public int createProduct(@RequestBody ProductDTO productDTO) {

        return productService.createProduct(productDTO);

    }


    /**
     * Handles deleting of Product with specific id
     * @param id of Product to be deleted
     * @return a {@link ResponseEntity} with the  HTTP status code
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id) {

            Product product = productService.findById(id);

            productService.deleteProduct(ProductDTO.from(product));

            return ResponseEntity.ok().build();

    }

    /**
     *
     * @param newProduct which takes place of previous Product
     * @param id of previous and new Product
     * @return a {@link ResponseEntity} with the  HTTP status code
     */
    @PutMapping("/{id}")
    public ResponseEntity<String> replaceProduct(@RequestBody Product newProduct, @PathVariable int id) {

            productService.replaceProduct(ProductDTO.from(newProduct), id);

            return ResponseEntity.ok().build();


    }




}
