package com.spirngboot.blog.Spring.service.impl;

import com.spirngboot.blog.Spring.Payload.CommentDto;
import com.spirngboot.blog.Spring.entity.Comment;
import com.spirngboot.blog.Spring.entity.Post;
import com.spirngboot.blog.Spring.exception.BlogAPIException;
import com.spirngboot.blog.Spring.exception.ResourceNotFoundException;
import com.spirngboot.blog.Spring.repository.CommentRepository;
import com.spirngboot.blog.Spring.repository.PostRespository;
import com.spirngboot.blog.Spring.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {


    private CommentRepository commentRepository;
    private PostRespository postRespository;

    public CommentServiceImpl(CommentRepository commentRepository, PostRespository postRespository) {
        this.commentRepository = commentRepository;
        this.postRespository = postRespository;
    }

    @Override
    public CommentDto createComment(long postId, CommentDto commentDto) {

        Comment comment = mapToEntity(commentDto);

        // retrieve post entity by id
        Post post = postRespository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));

        // set post to comment entity
        comment.setPost(post);

        // save comment entity to DB
        Comment newComment = commentRepository.save(comment);

        return mapToDTO(newComment);
    }


    @Override
    public List<CommentDto> getCommentsByPostId(Long postId) {

        // retrieve comments by postId
        List<Comment> comments = commentRepository.findByPostId(postId);

        //converting list of comment entities to list of comment dto entities
        return comments.stream().map(comment -> mapToDTO(comment)).collect(Collectors.toList());
    }

    @Override
    public CommentDto getCommentById(Long postId, Long commentId) {

        // retrieve post entity by id
        Post post = postRespository.findById(postId).
                orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));

        // retrieve comment entity by id
        Comment comment = commentRepository.findById(commentId).
                orElseThrow(() -> new ResourceNotFoundException("Comment", "id", commentId));

        // Checking comments belongs to particular post or not
        if (!comment.getPost().getId().equals(post.getId())) {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment doesn't belongs to the Post");
        }

        return mapToDTO(comment);

    }

    @Override
    public CommentDto updateComment(Long postId, Long commentId, CommentDto commentRequest) {
        // retrieve post entity by id
        Post post = postRespository.findById(postId).
                orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));

        // retrieve comment entity by id
        Comment comment = commentRepository.findById(commentId).
                orElseThrow(() -> new ResourceNotFoundException("Comment", "id", commentId));

        // Checking comments belongs to particular post or not
        if (!comment.getPost().getId().equals(post.getId())) {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment doesn't belongs to the Post");
        }

        comment.setName(commentRequest.getName());
        comment.setEmail(commentRequest.getEmail());
        comment.setBody(commentRequest.getBody());


        Comment updatedComment = commentRepository.save(comment);
        return mapToDTO(updatedComment);
    }

    @Override
    public void deleteComment(Long postId, Long commentId) {

        // retrieve post entity by id
        Post post = postRespository.findById(postId).
                orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));

        // retrieve comment entity by id
        Comment comment = commentRepository.findById(commentId).
                orElseThrow(() -> new ResourceNotFoundException("Comment", "id", commentId));

        // Checking comments belongs to particular post or not
        if (!comment.getPost().getId().equals(post.getId())) {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment doesn't belongs to the Post");
        }

        commentRepository.delete(comment);

    }


    // converting commententity to commentdto
    private CommentDto mapToDTO(Comment comment) {
        CommentDto commentDto = new CommentDto();
        commentDto.setId(comment.getId());
        commentDto.setName(comment.getName());
        commentDto.setEmail(comment.getEmail());
        commentDto.setBody(comment.getBody());
        return commentDto;
    }

    // converting commentDTO to commentEntity
    private Comment mapToEntity(CommentDto commentDto) {
        Comment comment = new Comment();
        comment.setId(commentDto.getId());
        comment.setName(commentDto.getName());
        comment.setEmail(commentDto.getEmail());
        comment.setBody(commentDto.getBody());
        return comment;
    }
}
