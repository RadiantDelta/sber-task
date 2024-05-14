package com.radiantdelta.sbertask.dao;

import com.radiantdelta.sbertask.domain.Product;
import com.radiantdelta.sbertask.dto.ProductDTO;

import java.util.List;

public interface ProductDAO {

    List<Product> findAll();
    Boolean exists(int id);
    Product find(int id);
    void save(ProductDTO productDTO);
    void delete(ProductDTO productDTO);
}
