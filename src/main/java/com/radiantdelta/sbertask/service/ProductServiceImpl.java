package com.radiantdelta.sbertask.service;

import com.radiantdelta.sbertask.dao.ProductDAO;
import com.radiantdelta.sbertask.domain.Product;
import com.radiantdelta.sbertask.dto.ProductDTO;
import com.radiantdelta.sbertask.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDAO productDAO;
    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getAllProducts() {
        log.info("productDAO.findAll() called");
        return productDAO.findAll();
    }



    @Override
    public Product findById(int id) {
        log.info("productDAO.find(id) called");
        return productDAO.find(id);
    }

    @Override
    public Product createProduct(ProductDTO productDTO) {
        log.info("productDAO.save(productDTO) called");
        log.info("DTO IN SERVICE ID " + productDTO.getId());
        productDTO = productDAO.save(productDTO);
        log.info("DTO IN SERVICE ID AFTER SAVE " + productDTO.getId());
        return productDTO.toProduct();
    }

    @Override
    public void deleteProduct(ProductDTO productDTO) {
        log.info("productDAO.delete(productDTO) called");
        productDAO.delete(productDTO);

    }

    @Override
    public Product replaceProduct(ProductDTO newProductDTO, int id) {
        log.info("productDAO.find(id) called");
        Product updateProduct = productDAO.find(id);

        updateProduct.setProductName(newProductDTO.getProductName());
        updateProduct.setId(newProductDTO.getId());
        updateProduct.setAmount(newProductDTO.getAmount());

        log.info("productDAO.save(ProductDTO.from(updateProduct)) called");
        return productDAO.save(ProductDTO.from(updateProduct)).toProduct();

    }

}
