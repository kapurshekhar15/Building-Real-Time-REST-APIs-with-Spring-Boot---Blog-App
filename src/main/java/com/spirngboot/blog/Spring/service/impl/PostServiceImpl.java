package com.spirngboot.blog.Spring.service.impl;

import com.spirngboot.blog.Spring.Payload.PostDto;
import com.spirngboot.blog.Spring.Payload.PostResponse;
import com.spirngboot.blog.Spring.entity.Category;
import com.spirngboot.blog.Spring.entity.Post;
import com.spirngboot.blog.Spring.exception.ResourceNotFoundException;
import com.spirngboot.blog.Spring.repository.CategoryRepository;
import com.spirngboot.blog.Spring.repository.PostRespository;
import com.spirngboot.blog.Spring.service.PostService;
import org.modelmapper.ModelMapper;
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

    private ModelMapper mapper;

    private CategoryRepository categoryRepository;

    // using contructor injection
    public PostServiceImpl(PostRespository postRespository, ModelMapper mapper
            , CategoryRepository categoryRepository) {
        this.postRespository = postRespository;
        this.mapper = mapper;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public PostDto createPost(PostDto postDto) {

        Category category = categoryRepository.findById(postDto.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category", "Id", postDto.getCategoryId()));

        // convert DTO  to entity
        Post post = mapToEntity(postDto);
        post.setCategory(category);
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

        Category category = categoryRepository.findById(postDto.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", postDto.getCategoryId()));


        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        Post updatedPost = postRespository.save(post);
        post.setCategory(category);
        return mapToDto(updatedPost);
    }

    @Override
    public void postDeleteById(Long id) {
        // get the post by id from the database
        Post post = postRespository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        postRespository.delete(post);
    }


    // converting entity to DTO ---> using modelmapper to map one type to other type....!!!
    private PostDto mapToDto(Post post) {
        PostDto postDto = mapper.map(post, PostDto.class);


//        postDto.setId(post.getId());
//        postDto.setTitle(post.getTitle());
//        postDto.setDescription(post.getDescription());
//        postDto.setContent(post.getContent());

        return postDto;
    }


    // converting DTO to entity ---> using modelmapper to map one type to other type....!!!
    private Post mapToEntity(PostDto postDto) {

        Post post = mapper.map(postDto, Post.class);


//        post.setTitle(postDto.getTitle());
//        post.setDescription(postDto.getDescription());
//        post.setContent(postDto.getContent());

        return post;
    }


    @Override
    public List<PostDto> getPostByCategory(Long categoryId) {

// Checking if category with given categoryId exis in dB or not
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));
        List<Post> posts = postRespository.findByCategoryId(categoryId);


        return posts.stream().map((post) -> mapToDto(post))
                .collect(Collectors.toList());
    }


}
