package xyz.y2sec.blog.comment.controller;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.y2sec.blog.comment.dto.CommentDto;
import xyz.y2sec.blog.comment.dto.CommentResponse;
import xyz.y2sec.blog.comment.model.Comment;
import xyz.y2sec.blog.comment.service.CommentService;
import xyz.y2sec.blog.common.dto.ApiResult;
import xyz.y2sec.blog.post.service.PostService;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "api/comment")
@RequiredArgsConstructor
public class CommentController {

    private final PostService postService;

    private final CommentService commentService;

    @GetMapping
    public ResponseEntity<ApiResult> getCommentByPostId(@RequestParam(name = "postId") long postId) {
        List<CommentResponse> commentsByPostId = commentService
                .findByPostId(postId)
                .stream()
                .map(CommentResponse::new)
                .collect(Collectors.toList());

        return new ResponseEntity<>(ApiResult
                    .builder()
                    .data(commentsByPostId)
                    .message("success")
                    .build(), HttpStatus.OK);
    }

    @PostMapping(path = "/add")
    public ResponseEntity<ApiResult> createComment(@RequestBody @Valid CommentDto commentDto) {
        Comment comment = new Comment(commentDto, postService.findById(commentDto.getPostId()));
        commentService.addComment(comment);

        return new ResponseEntity<>(ApiResult
                .builder()
                .data(new CommentResponse(comment))
                .message("success")
                .build(), HttpStatus.OK);
    }

    @PutMapping(path = "/modify")
    public ResponseEntity<ApiResult> modifyPost(@RequestBody @Valid CommentDto commentDto) {
        long modifyCommentId = commentService.modifyComment(commentDto);

        Comment modifyComment = commentService.findById(modifyCommentId);

        return new ResponseEntity<>(ApiResult
                .builder()
                .data(new CommentResponse(modifyComment))
                .message("success")
                .build(), HttpStatus.OK);
    }

    @DeleteMapping(path = "/delete")
    public ResponseEntity<ApiResult> deletePost(@RequestParam(name = "id") long id,
                                                @RequestParam(name = "pw") String password) {
        Comment deleteComment = commentService.findById(id);

        if (StringUtils.equals(deleteComment.getPassword(),password)) {
            commentService.removeComment(id);

            return new ResponseEntity<>(ApiResult
                    .builder()
                    .data(null)
                    .message("success")
                    .build(), HttpStatus.OK);
        }

        return new ResponseEntity<>(ApiResult
                .builder()
                .data(null)
                .message("fail")
                .build(), HttpStatus.OK);
    }
}
