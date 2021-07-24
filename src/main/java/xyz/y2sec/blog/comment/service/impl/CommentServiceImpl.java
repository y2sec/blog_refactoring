package xyz.y2sec.blog.comment.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.y2sec.blog.comment.dto.CommentDto;
import xyz.y2sec.blog.comment.model.Comment;
import xyz.y2sec.blog.comment.repository.CommentRepository;
import xyz.y2sec.blog.comment.service.CommentService;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Comment> findByPostId(long postId) {
        return commentRepository.findByPostId(postId);
    }

    @Override
    @Transactional(readOnly = true)
    public Comment findById(long commentId) {
        Optional<Comment> findComment = commentRepository.findById(commentId);

        if (findComment.isEmpty()) {
            throw new EntityNotFoundException();
        }
        return findComment.get();
    }

    @Override
    public long addComment(Comment comment) {
        Comment saveComment = commentRepository.save(comment);

        return saveComment.getId();
    }

    @Override
    public long modifyComment(CommentDto commentDto) {
        Optional<Comment> findComment = commentRepository.findById(commentDto.getId());

        if (findComment.isEmpty()) {
            throw new EntityNotFoundException();
        }
        findComment.get().update(commentDto);

        return findComment.get().getId();
    }

    @Override
    public void removeComment(long commentId) {
        Optional<Comment> findComment = commentRepository.findById(commentId);

        if (findComment.isEmpty()) {
            throw new EntityNotFoundException();
        }
        commentRepository.delete(findComment.get());
    }
}
