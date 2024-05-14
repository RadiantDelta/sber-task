package com.radiantdelta.sbertask.dao;

import com.radiantdelta.sbertask.domain.Product;
import com.radiantdelta.sbertask.dto.ProductDTO;
import com.radiantdelta.sbertask.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductDAOImpl implements ProductDAO{
    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Boolean exists(int id) {
        return productRepository.existsById(id);
    }

    @Override
    public Product find(int id) {
        return productRepository.findById(id);
    }

    @Override
    public void save(ProductDTO productDTO) {
        productRepository.save(productDTO.toProduct());
    }

    @Override
    public void delete(ProductDTO productDTO) {
        productRepository.delete(productDTO.toProduct());
    }
}
