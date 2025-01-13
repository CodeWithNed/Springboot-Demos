package com.nadun.ims.repository;

import com.nadun.ims.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    // Find users by name (supports partial matches)
    List<Order> findByOrderNameContaining(String name);

    // Check if a user exists by userId
    boolean existsById(Long id);
}
