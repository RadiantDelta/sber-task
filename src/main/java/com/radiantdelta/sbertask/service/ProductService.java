package com.radiantdelta.sbertask.service;

import com.radiantdelta.sbertask.domain.Product;
import com.radiantdelta.sbertask.dto.ProductDTO;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public interface ProductService {
    /**
     * Saves a given entity
     * @param productDTO entity which is to be saved
     * @return saved entity
     */
    Product createProduct(ProductDTO productDTO);

    /**
     * Deletes a given entity
     * @param productDTO entity which is to be deleted
     */
    void deleteProduct(ProductDTO productDTO);

    /**
     * Replaces existing Product with specific id
     * with  new Product,
     * id in param and id in newProductDTO must be the same
     * @param newProductDTO new entity instead of previous
     * @param id identificator of previous and new entity
     * @return  Product
     */
    Product replaceProduct( ProductDTO newProductDTO,  int id);

    /**
     *  Returns all instances of Product
     * @return list of all instances of Product
     */
    List<Product> getAllProducts();

    /**
     * Returns the Product with the specific id
     * @param id id of Product to search for
     * @return  Product with the specific id
     */
    Product findById(int id);

}
