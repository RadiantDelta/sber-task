package com.radiantdelta.sbertask.controller;

import com.radiantdelta.sbertask.domain.Product;
import com.radiantdelta.sbertask.dto.ProductDTO;
import com.radiantdelta.sbertask.service.ProductService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@Slf4j
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
        Product p = productService.findById(id);
        return (p == null) ? (ResponseEntity<Product>) ResponseEntity.noContent() : ResponseEntity.ok(p);


    }

    /**
     *  Handles creation of new Product
     * @param product the details of Product to be created
     * @return  Product or redirect
     */
    @PostMapping
    @Transactional
    public ResponseEntity createProduct(@RequestBody Product product) {
        log.info("request body product id : " +product.getId());
        Product result = productService.createProduct(ProductDTO.from(product));

         return ResponseEntity.created(URI.create("/" + result.getId())).body(result);



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
     * Handles updating of Product with specific id
     * @param newProduct which takes place of previous Product
     * @param id of previous and new Product
     * @return updated Product with the  HTTP status code
     */
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Product> replaceProduct(@RequestBody Product newProduct, @PathVariable int id) {

           Product result = productService.replaceProduct(ProductDTO.from(newProduct), id);

            return ResponseEntity.ok().body(result);


    }




}
