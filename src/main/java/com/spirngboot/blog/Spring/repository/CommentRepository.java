package com.spirngboot.blog.Spring.repository;

import com.spirngboot.blog.Spring.entity.Comment;
import org.springframework.boot.autoconfigure.jackson.JacksonProperties;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// no need to provide @Repository annotation because we have simpleJpaRepository
// class in spring data jap package, which implements internally JpaReposritory
// and simpleJpaRepository class already annotated with @Repository and @Transaction

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByPostId(Long postId);
}
