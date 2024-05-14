package com.radiantdelta.sbertask.controller;

import com.radiantdelta.sbertask.domain.Product;
import com.radiantdelta.sbertask.dto.ProductDTO;
import com.radiantdelta.sbertask.repository.ProductRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping()
public class ProductController {
    @Autowired
    private ProductRepository productRepository;
    @PersistenceContext
    private EntityManager entityManager;

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> result =productRepository.findAll();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable int id) {

        if (productRepository.existsById(id)) {
            return ResponseEntity.ok(productRepository.findById(id));
        }
        return ResponseEntity.notFound().build();


    }

    @PostMapping
    public int createProduct(@RequestBody ProductDTO productDTO) {
      //  entityManager.persist(productDTO.toProduct());
      //  entityManager.flush();
        Product product = productDTO.toProduct();
        productRepository.save(product);
        return productDTO.getId();
    }


   // @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id) {
        if (productRepository.existsById(id)) {
            Product product = productRepository.findById(id);

            productRepository.delete(product);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();

    }

    @PutMapping("/{id}")
    public ResponseEntity<String> replaceProduct(@RequestBody Product newProduct, @PathVariable int id) {
        if (productRepository.existsById(id)) {
            Product updateProduct = productRepository.findById(id);

            updateProduct.setProductName(newProduct.getProductName());
            updateProduct.setId(newProduct.getId());
            updateProduct.setAmount(newProduct.getAmount());

            productRepository.save(updateProduct);

            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();

    }






}
