package com.spirngboot.blog.Spring.service;

import com.spirngboot.blog.Spring.Payload.PostDto;
import com.spirngboot.blog.Spring.Payload.PostResponse;

import java.util.List;

public interface PostService {

    PostDto createPost(PostDto postDto);

    PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir);

    PostDto getPostById(Long id);

    PostDto updatePost(PostDto postDto, long id);

    void postDeleteById(Long id);
}
