package com.codecool.hotel_backend.repository;

import com.codecool.hotel_backend.entity.SecuUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<SecuUser, String> {
}
