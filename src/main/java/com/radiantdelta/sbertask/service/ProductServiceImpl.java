package com.radiantdelta.sbertask.service;

import com.radiantdelta.sbertask.domain.Product;
import com.radiantdelta.sbertask.dto.ProductDTO;
import com.radiantdelta.sbertask.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }


    @Override
    public Boolean existsById(int id) {
        return productRepository.existsById(id);
    }

    @Override
    public Product findById(int id) {
        return productRepository.findById(id);
    }

    @Override
    public int createProduct(ProductDTO productDTO) {
        Product product = productDTO.toProduct();
        productRepository.save(product);
        return productDTO.getId();
    }

    @Override
    public void deleteProduct(ProductDTO productDTO) {
        productRepository.delete(productDTO.toProduct());

    }

    @Override
    public void replaceProduct(ProductDTO newProductDTO, int id) {
        Product updateProduct = productRepository.findById(id);

        updateProduct.setProductName(newProductDTO.getProductName());
        updateProduct.setId(newProductDTO.getId());
        updateProduct.setAmount(newProductDTO.getAmount());

        productRepository.save(updateProduct);
    }

}
