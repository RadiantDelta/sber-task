package com.radiantdelta.sbertask.service;

import com.radiantdelta.sbertask.dao.ProductDAO;
import com.radiantdelta.sbertask.domain.Product;
import com.radiantdelta.sbertask.dto.ProductDTO;
import com.radiantdelta.sbertask.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDAO productDAO;
    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getAllProducts() {
        return productDAO.findAll();
    }


    @Override
    public Boolean existsById(int id) {
        return productDAO.exists(id);
    }

    @Override
    public Product findById(int id) {
        return productDAO.find(id);
    }

    @Override
    public int createProduct(ProductDTO productDTO) {
        productDAO.save(productDTO);
        return productDTO.getId();
    }

    @Override
    public void deleteProduct(ProductDTO productDTO) {
        productDAO.delete(productDTO);

    }

    @Override
    public void replaceProduct(ProductDTO newProductDTO, int id) {
        Product updateProduct = productDAO.find(id);

        updateProduct.setProductName(newProductDTO.getProductName());
        updateProduct.setId(newProductDTO.getId());
        updateProduct.setAmount(newProductDTO.getAmount());

        productDAO.save(ProductDTO.from(updateProduct));
    }

}
