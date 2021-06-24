package xyz.y2sec.blog.comment.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import xyz.y2sec.blog.comment.dto.CommentDto;
import xyz.y2sec.blog.comment.model.Comment;
import xyz.y2sec.blog.post.model.Post;
import xyz.y2sec.blog.post.service.PostService;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import java.util.List;

@SpringBootTest
@Transactional
class CommentServiceTest {

    @Autowired
    private PostService postService;

    @Autowired
    private CommentService commentService;

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    @DisplayName("게시물 ID로 조회")
    void findByPostId() {
        // given
        Post post = new Post();
        long postId = postService.addPost(post);

        CommentDto commentDto = new CommentDto("이름", "내용", "비밀번호");

        Comment comment1 = new Comment(commentDto, post);
        Comment comment2 = new Comment(commentDto, post);
        Comment comment3 = new Comment(commentDto ,post);

        commentService.addComment(comment1);
        commentService.addComment(comment2);
        commentService.addComment(comment3);

        // when
        List<Comment> comments1 = commentService.findByPostId(postId);
        List<Comment> comments2 = commentService.findByPostId(10);

        // then
        Assertions.assertEquals(comments1.size(), 3);
        Assertions.assertEquals(comments2.size(), 0);

    }

    @Test
    @DisplayName("댓글 추가")
    void addComment() {
        // given
        Comment comment = new Comment();

        // when
        long commentId = commentService.addComment(comment);

        Comment findComment = commentService.findById(commentId);

        // then
        Assertions.assertEquals(comment, findComment);

    }

    @Test
    @DisplayName("댓글 조회")
    void findComment() {
        // given
        Comment comment1 = new Comment();
        Comment comment2 = new Comment();
        Comment comment3 = new Comment();

        // when
        commentService.addComment(comment1);
        commentService.addComment(comment2);
        commentService.addComment(comment3);

        List<Comment> comments = commentService.findAll();

        // then
        Assertions.assertEquals(comments.size(), 3);

    }

    @Test
    @DisplayName("댓글 수정")
    void modifyComment() {
        // given
        Post post = new Post();
        postService.addPost(post);

        CommentDto beforeCommentDto = new CommentDto("이전 이름", "이전 내용", "이전 비밀번호");
        Comment comment = new Comment(beforeCommentDto, post);

        // when
        long commentId = commentService.addComment(comment);

        CommentDto afterCommentDto = new  CommentDto("이후 이름", "이후 내용", "이후 비밀번호");
        comment.update(afterCommentDto);

        entityManager.flush();
        entityManager.clear();

        Comment findComment = commentService.findById(commentId);

        // then
        Assertions.assertEquals(findComment.getName(), "이후 이름");
        Assertions.assertEquals(findComment.getContent(), "이후 내용");
        Assertions.assertEquals(findComment.getPassword(), "이후 비밀번호");

    }

    @Test
    @DisplayName("댓글 삭제")
    void deleteComment() {
        // given
        Comment comment = new Comment();
        long commentId = commentService.addComment(comment);

        // when
        commentService.removeComment(commentId);

        // then
        Assertions.assertThrows(EntityNotFoundException.class, () -> commentService.findById(commentId));
    }
}