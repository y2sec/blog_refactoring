package xyz.y2sec.blog.comment.service;

import xyz.y2sec.blog.comment.dto.CommentDto;
import xyz.y2sec.blog.comment.model.Comment;

import java.util.List;

public interface CommentService {

    List<Comment> findAll();

    List<Comment> findByPostId(long postId);

    Comment findById(long commentId);

    long addComment(Comment comment);

    long modifyComment(CommentDto commentDto);

    void removeComment(long commentId);

}
