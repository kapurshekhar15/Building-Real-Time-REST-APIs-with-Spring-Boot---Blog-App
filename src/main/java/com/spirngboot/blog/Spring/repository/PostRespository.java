package com.spirngboot.blog.Spring.repository;

import com.spirngboot.blog.Spring.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRespository extends JpaRepository<Post, Long> {
}
