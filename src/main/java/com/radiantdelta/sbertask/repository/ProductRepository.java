package com.radiantdelta.sbertask.repository;

import com.radiantdelta.sbertask.domain.Product;
import org.springframework.data.annotation.Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
    List<Product> findAll();
    Optional<Product> findById(int id);
    void delete(Product product);
    Boolean existsById(int id);
}
