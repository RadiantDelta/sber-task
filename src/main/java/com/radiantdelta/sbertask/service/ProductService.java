package com.radiantdelta.sbertask.service;

import com.radiantdelta.sbertask.domain.Product;
import com.radiantdelta.sbertask.dto.ProductDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public interface ProductService {
    int createProduct(ProductDTO productDTO);
    void deleteProduct(ProductDTO productDTO);
    void replaceProduct( ProductDTO newProductDTO,  int id);

    List<Product> getAllProducts();

    Boolean existsById(int id);
    Product findById(int id);

}
