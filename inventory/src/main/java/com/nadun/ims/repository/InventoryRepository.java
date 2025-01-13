package com.nadun.ims.repository;

import com.nadun.ims.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    // Find users by name (supports partial matches)
    List<Inventory> findByInventoryNameContaining(String name);

    // Check if a user exists by userId
    boolean existsById(Long id);
}
