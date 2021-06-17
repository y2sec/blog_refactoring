package xyz.y2sec.blog.post.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import xyz.y2sec.blog.category.model.Category;
import xyz.y2sec.blog.post.dto.PostDto;
import xyz.y2sec.blog.post.model.Post;
import xyz.y2sec.blog.post.repository.PostRepository;
import xyz.y2sec.blog.post.service.PostService;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    @Override
    public List<Post> findAll() {
        return postRepository.findAll();
    }

    @Override
    public List<Post> findByCategoryId(long categoryId) {
        return postRepository.findByCategoryId(categoryId);
    }

    @Override
    public Post findById(long postId) {
        Optional<Post> findPost = postRepository.findById(postId);

        if (findPost.isEmpty()) {
            throw new EntityNotFoundException();
        }
        return findPost.get();
    }

    @Override
    public long addPost(Post post) {
        Post savePost = postRepository.save(post);

        return savePost.getId();
    }

    @Override
    public void modifyPost(PostDto postDto, Category category, long postId) {
        Optional<Post> findPost = postRepository.findById(postId);

        if (findPost.isEmpty()) {
            throw new EntityNotFoundException();
        }
        findPost.get().update(postDto, category);
    }

    @Override
    public void removePost(long postId) {
        Optional<Post> findPost = postRepository.findById(postId);

        if (findPost.isEmpty()) {
            throw new EntityNotFoundException();
        }
        postRepository.delete(findPost.get());
    }
}
