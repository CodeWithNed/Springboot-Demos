package com.nadun.ims.repository;

import com.nadun.ims.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Find users by name (supports partial matches)
    List<User> findByUserNameContaining(String name);

    // Check if a user exists by userId
    boolean existsById(Long id);
}
