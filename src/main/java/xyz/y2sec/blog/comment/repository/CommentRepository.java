package xyz.y2sec.blog.comment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.y2sec.blog.comment.model.Comment;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByPostId(long postId);

}
