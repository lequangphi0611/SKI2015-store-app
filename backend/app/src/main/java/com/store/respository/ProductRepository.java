package com.store.respository;

import java.util.Collection;

import com.store.entity.Product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    boolean existsByName(String name);

    Collection<Product> findAllByCategoryId(long id);

    Collection<Product> findAllByCategoryIdAndNameContaining(long categoryId, String query);

    Collection<Product> findAllByNameContaining(String query);

}