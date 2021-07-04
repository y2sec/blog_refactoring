package xyz.y2sec.blog.post.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import xyz.y2sec.blog.category.model.Category;
import xyz.y2sec.blog.category.service.CategoryService;
import xyz.y2sec.blog.post.dto.PostDto;
import xyz.y2sec.blog.post.model.Post;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import java.util.List;

@SpringBootTest
@Transactional
class PostServiceTest {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private PostService postService;

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    @DisplayName("카테고리 ID로 조회")
    void findByCategoryId() {
        // given
        Category category = new Category();
        long categoryId = categoryService.addCategory(category);

        PostDto postDto = new PostDto("제목", "내용", categoryId);

        Post post1 = new Post(postDto, category);
        Post post2 = new Post(postDto, category);
        Post post3 = new Post(postDto ,category);

        postService.addPost(post1);
        postService.addPost(post2);
        postService.addPost(post3);

        // when
        List<Post> categories1 = postService.findByCategoryId(categoryId);
        List<Post> categories2 = postService.findByCategoryId(10);

        // then
        Assertions.assertEquals(categories1.size(), 3);
        Assertions.assertEquals(categories2.size(), 0);

    }

    @Test
    @DisplayName("게시물 추가")
    void addPost() {
        // given
        Post post = new Post();
        long postId = postService.addPost(post);

        // when
        Post findPost = postService.findById(postId);

        // then
        Assertions.assertEquals(findPost, post);
    }

    @Test
    @DisplayName("게시물 조회")
    void findPost() {
        // given
        Post post1 = new Post();
        Post post2 = new Post();
        Post post3 = new Post();

        postService.addPost(post1);
        postService.addPost(post2);
        postService.addPost(post3);

        // when
        List<Post> posts = postService.findAll();

        // then
        Assertions.assertEquals(posts.size(), 3);
    }

    @Test
    @DisplayName("게시물 수정")
    void updatePost() {
        // given
        PostDto beforePostDto = new PostDto("이전제목", "이전내용", 1);
        Category category = new Category();
        categoryService.addCategory(category);

        Post post = new Post(beforePostDto, category);
        long postId = postService.addPost(post);

        // when
        PostDto afterPostDto = new PostDto("이후제목", "이후내용", 1);
        postService.modifyPost(afterPostDto, category, postId);
        entityManager.flush();
        entityManager.clear();

        Post findPost = postService.findById(postId);

        // then
        Assertions.assertEquals(findPost.getTitle(), "이후제목");
        Assertions.assertEquals(findPost.getContent(), "이후내용");
    }

    @Test
    @DisplayName("게시물 삭제")
    void deletePost() {
        // given
        Post post = new Post();
        long postId = postService.addPost(post);

        // when
        postService.removePost(postId);

        // then
        Assertions.assertThrows(EntityNotFoundException.class, () -> postService.findById(postId));
    }

}