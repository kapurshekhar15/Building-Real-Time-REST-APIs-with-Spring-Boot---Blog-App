package com.spirngboot.blog.Spring.controller;


import com.spirngboot.blog.Spring.Payload.PostDto;
import com.spirngboot.blog.Spring.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private PostService postService;

    // we are configuring PostController class as a spring bean and it has only one constructor then we can omit @Autowired annotation
    public PostController(PostService postService) {
        this.postService = postService;
    }


    // Create blog post
    @PostMapping
    public ResponseEntity<PostDto> cratePost(@RequestBody PostDto postDto) {

        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);

    }

    // Get all posts
    @GetMapping
    public List<PostDto> getAllPosts() {
        return postService.getAllPosts();
    }

    // Get postById
    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(postService.getPostById(id));
    }

    // update the post by id
    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable(name = "id") long id) {
        PostDto postResponse = postService.updatePost(postDto, id);
        return new ResponseEntity<>(postResponse, HttpStatus.OK);
    }

    // deletepost by id
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable(name = "id") long id) {
        postService.postDeleteById(id);
        return new ResponseEntity<>("Post entity deleted successfuly", HttpStatus.OK);
    }
}
