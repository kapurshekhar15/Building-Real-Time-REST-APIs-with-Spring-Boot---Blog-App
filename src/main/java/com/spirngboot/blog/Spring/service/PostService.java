package com.spirngboot.blog.Spring.service;

import com.spirngboot.blog.Spring.Payload.PostDto;

import java.util.List;

public interface PostService {

    PostDto createPost(PostDto postDto);

    List<PostDto> getAllPosts();

    PostDto getPostById(Long id);

    PostDto updatePost(PostDto postDto, long id);

    void postDeleteById(Long id);
}
