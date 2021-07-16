package xyz.y2sec.blog.post.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.y2sec.blog.category.service.CategoryService;
import xyz.y2sec.blog.common.dto.ApiResult;
import xyz.y2sec.blog.post.dto.PostDto;
import xyz.y2sec.blog.post.dto.PostResponse;
import xyz.y2sec.blog.post.model.Post;
import xyz.y2sec.blog.post.service.PostService;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "api/post")
@RequiredArgsConstructor
public class PostController {

    private final CategoryService categoryService;

    private final PostService postService;

    @GetMapping
    public ResponseEntity<ApiResult> getPosts(
            @RequestParam(name = "categoryId", required = false, defaultValue = "0") long categoryId,
            @RequestParam(name = "start", required = false, defaultValue = "0") int start) {

        if (categoryId == 0) {
            List<PostResponse> posts = postService
                    .findAll()
                    .stream()
                    .map(PostResponse::new)
                    .collect(Collectors.toList());

            return new ResponseEntity<>(ApiResult
                    .builder()
                    .data(posts)
                    .message("success")
                    .build(), HttpStatus.OK);
        }
        List<PostResponse> postsByCategoryId = postService
                .findByCategoryId(categoryId)
                .stream()
                .map(PostResponse::new)
                .collect(Collectors.toList());

        return new ResponseEntity<>(ApiResult
                .builder()
                .data(postsByCategoryId)
                .message("success")
                .build(), HttpStatus.OK);
    }

    @GetMapping(path = "/{postId}")
    public ResponseEntity<ApiResult> getPostById(@PathVariable(name = "postId") long id) {
        Post findPost = postService.findById(id);

        return new ResponseEntity<>(ApiResult
                .builder()
                .data(new PostResponse(findPost))
                .message("success")
                .build(), HttpStatus.OK);
    }

    @PostMapping(path = "/add")
    public ResponseEntity<ApiResult> createPost(@RequestBody @Valid PostDto postDto) {
        Post post = new Post(postDto, categoryService.findById(postDto.getCategoryId()));
        long postId = postService.addPost(post);

        Post createPost = postService.findById(postId);

        return new ResponseEntity<>(ApiResult
                .builder()
                .data(new PostResponse(createPost))
                .message("success")
                .build(), HttpStatus.OK);
    }

    @PutMapping(path = "/modify")
    public ResponseEntity<ApiResult> modifyPost(@RequestBody @Valid PostDto postDto) {
        long modifyPostId = postService.modifyPost(postDto, categoryService.findById(postDto.getCategoryId()));

        Post modifyPost = postService.findById(modifyPostId);

        return new ResponseEntity<>(ApiResult
                .builder()
                .data(new PostResponse(modifyPost))
                .message("success")
                .build(), HttpStatus.OK);
    }

    @DeleteMapping(path = "/delete")
    public ResponseEntity<ApiResult> deletePost(@RequestParam(name = "postId") long id) {
        postService.removePost(id);

        return new ResponseEntity<>(ApiResult
                .builder()
                .data(null)
                .message("success")
                .build(), HttpStatus.OK);
    }
}
