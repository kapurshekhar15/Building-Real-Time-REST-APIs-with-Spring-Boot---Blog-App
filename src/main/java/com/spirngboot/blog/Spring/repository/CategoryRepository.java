package com.spirngboot.blog.Spring.repository;

import com.spirngboot.blog.Spring.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
