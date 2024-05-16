package com.radiantdelta.sbertask.dao;

import com.radiantdelta.sbertask.domain.Product;
import com.radiantdelta.sbertask.dto.ProductDTO;

import java.util.List;
import java.util.Optional;

/**
 * The DAO interface defines abstract methods
 * that perform the create, read, update and delete operations
 */
public interface ProductDAO {
    /**
     *  Returns all instances of Product
     * @return list of all instances of Product
     */
    List<Product> findAll();

    /**
     * Returns the Product with the specific id
     * @param id id of Product to search for
     * @return  Product with the specific id
     */
    Product find(int id);

    /**
     * Saves a given entity
     * @param productDTO entity which will be saved
     * @return  saved product
     */
    ProductDTO save(ProductDTO productDTO);

    /**Deletes a given entity
     *
     * @param productDTO entity which will be deleted
     */
    void delete(ProductDTO productDTO);
}
