package xyz.y2sec.blog.post.service;

import xyz.y2sec.blog.category.model.Category;
import xyz.y2sec.blog.post.dto.PostDto;
import xyz.y2sec.blog.post.model.Post;

import java.util.List;

public interface PostService {

    List<Post> findAll();

    List<Post> findByCategoryId(long categoryId);

    Post findById(long postId);

    long addPost(Post post);

    long modifyPost(PostDto postDto, Category category);

    void removePost(long postId);

}
