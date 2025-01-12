package com.nadun.ims.repository;

import com.nadun.ims.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    // Find users by name (supports partial matches)
    List<Product> findByProductNameContaining(String name);

    // Check if a user exists by userId
    boolean existsById(Long id);
}
