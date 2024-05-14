package com.radiantdelta.sbertask.dao;

import com.radiantdelta.sbertask.domain.Product;
import com.radiantdelta.sbertask.dto.ProductDTO;
import com.radiantdelta.sbertask.exception.ResourceNotFoundException;
import com.radiantdelta.sbertask.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class ProductDAOImpl implements ProductDAO{
    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> findAll() {
        log.info("called productRepository.findAll()");
        return productRepository.findAll();
    }


    @Override
    public Product find(int id) {
        log.info("called productRepository.findById(id)");
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not Found: " + id));
    }

    @Override
    public void save(ProductDTO productDTO) {
        log.info("called productRepository.save(productDTO.toProduct())");

        productRepository.save(productDTO.toProduct());
    }

    @Override
    public void delete(ProductDTO productDTO) {
        log.info("called productRepository.delete(productDTO.toProduct())");
        productRepository.delete(productDTO.toProduct());
    }
}
