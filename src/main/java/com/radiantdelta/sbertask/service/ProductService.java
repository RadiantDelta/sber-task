package com.radiantdelta.sbertask.service;

import com.radiantdelta.sbertask.domain.Product;
import com.radiantdelta.sbertask.dto.ProductDTO;
import org.springframework.stereotype.Service;


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
