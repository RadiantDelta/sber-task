package com.radiantdelta.sbertask.repository;

import com.radiantdelta.sbertask.domain.Product;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.data.annotation.Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Set of methods to manipulate Products
 */
@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
    /**
     * Returns all entities of Product
     * @return all entities
     */
    List<Product> findAll();

    /**
     * Retrieves Product  by its id.
     *
     * @param id must not be {@literal null}.
     * @return the entity with the given id or {@literal Optional#empty()} if none found.
     * @throws IllegalArgumentException if {@literal id} is {@literal null}.
     */
    Optional<Product> findById(int id);

    /**
     * Deletes a given Product.
     *
     * @param product must not be {@literal null}.
     * @throws IllegalArgumentException in case the given product is {@literal null}.
     * @throws OptimisticLockingFailureException when the product uses optimistic locking and has a version attribute with
     *           a different value from that found in the persistence store. Also thrown if the product is assumed to be
     *           present but does not exist in the database.
     */
    void delete(Product product);

}
