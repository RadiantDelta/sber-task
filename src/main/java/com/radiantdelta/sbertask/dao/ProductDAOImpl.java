package com.radiantdelta.sbertask.dao;

import com.radiantdelta.sbertask.domain.Product;
import com.radiantdelta.sbertask.dto.ProductDTO;
import com.radiantdelta.sbertask.exception.NoContentException;
import com.radiantdelta.sbertask.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

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
                .orElseThrow(() -> new NoContentException("Not Found: " + id));
    }

    @Override
    public ProductDTO save(ProductDTO productDTO) {
        log.info("called productRepository.save(productDTO.toProduct()) with product Id="+productDTO.getId());
        Product p = productRepository.save(productDTO.toProduct());
       // log.info(p.getId() + "---------------------------------"+productDTO.getId());
        productRepository.flush();
        log.info(p.getId() + "----------------- returned id from db");
        return ProductDTO.from(p);

    }

    @Override
    public void delete(ProductDTO productDTO) {
        log.info("called productRepository.delete(productDTO.toProduct())");
        productRepository.delete(productDTO.toProduct());
    }
}
