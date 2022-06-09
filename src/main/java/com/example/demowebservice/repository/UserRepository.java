package com.example.demowebservice.repository;

import com.example.demowebservice.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
     User findByEmail(String email);
}
