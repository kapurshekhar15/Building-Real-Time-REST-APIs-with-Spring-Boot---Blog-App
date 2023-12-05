package com.spirngboot.blog.Spring.service.impl;

import com.spirngboot.blog.Spring.Payload.PostDto;
import com.spirngboot.blog.Spring.Payload.PostResponse;
import com.spirngboot.blog.Spring.entity.Post;
import com.spirngboot.blog.Spring.exception.ResourceNotFoundException;
import com.spirngboot.blog.Spring.repository.PostRespository;
import com.spirngboot.blog.Spring.service.PostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private PostRespository postRespository;

    // using contructor injection
    public PostServiceImpl(PostRespository postRespository) {
        this.postRespository = postRespository;
    }

    @Override
    public PostDto createPost(PostDto postDto) {

        // convert DTO  to entity
        Post post = mapToEntity(postDto);
        Post newPost = postRespository.save(post);

        // entity to DTO
        PostDto postResponse = mapToDto(newPost);
        return postResponse;
    }

    @Override
    public PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        // create pageble instance
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<Post> posts = postRespository.findAll(pageable);

        // get content for page object

        List<Post> listOfPosts = posts.getContent();

        List<PostDto> content = listOfPosts.stream().map(post -> mapToDto(post)).collect(Collectors.toList());
        PostResponse postResponse = new PostResponse();
        postResponse.setContent(content);
        postResponse.setPageNo(posts.getNumber());
        postResponse.setPageSize(posts.getSize());
        postResponse.setTotalElements(posts.getTotalElements());
        postResponse.setTotalPages(posts.getTotalPages());
        postResponse.setLast(posts.isLast());

        return postResponse;

    }

    @Override
    public PostDto getPostById(Long id) {

        Post post = postRespository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        return mapToDto(post);
    }

    @Override
    public PostDto updatePost(PostDto postDto, long id) {

        // get the POST by id
        Post post = postRespository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));

        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        Post updatedPost = postRespository.save(post);

        return mapToDto(updatedPost);
    }

    @Override
    public void postDeleteById(Long id) {
        // get the post by id from the database
        Post post = postRespository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        postRespository.delete(post);
    }


    // converting entity to DTO
    private PostDto mapToDto(Post post) {
        PostDto postDto = new PostDto();
        postDto.setId(post.getId());
        postDto.setTitle(post.getTitle());
        postDto.setDescription(post.getDescription());
        postDto.setContent(post.getContent());

        return postDto;
    }


    // converting DTO to entity
    private Post mapToEntity(PostDto postDto) {

        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());

        return post;
    }


}
