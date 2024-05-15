package com.radiantdelta.sbertask.dto;

import com.radiantdelta.sbertask.domain.Product;
import lombok.Data;

/**
 *DTO is an object used to transfer data between different layers or components of an application.
 * It encapsulates data and provides a standardized way of exchanging information between different parts of the system
 *
 */
@Data
public class ProductDTO {
    private int id;
    private String productName;
    private int amount;

    public static ProductDTO from(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setProductName(product.getProductName());
        productDTO.setAmount(product.getAmount());
        return productDTO;
    }

    public Product toProduct() {
        Product product = new Product();
        product.setId(this.id);
        product.setProductName(this.productName);
        product.setAmount(this.amount);
        return product;
    }
}
