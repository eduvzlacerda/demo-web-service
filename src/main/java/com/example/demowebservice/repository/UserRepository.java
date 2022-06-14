package com.example.demowebservice.repository;

import com.example.demowebservice.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {
     UserEntity findByEmail(String email);
}
