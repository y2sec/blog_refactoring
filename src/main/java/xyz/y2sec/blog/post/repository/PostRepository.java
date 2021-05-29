package xyz.y2sec.blog.post.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.y2sec.blog.post.model.Post;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findByCategoryId(long categoryId);

}
