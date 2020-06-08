package com.codecool.hotel_backend.repository;

import com.codecool.hotel_backend.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
