package xyz.y2sec.blog.comment.dto;

import lombok.Getter;
import xyz.y2sec.blog.comment.model.Comment;

import java.time.LocalDateTime;

@Getter
public class CommentResponse {

    public CommentResponse(Comment comment) {
        this.id = comment.getId();
        this.name = comment.getName();
        this.content = comment.getContent();
        this.createDate = comment.getCreateDate();
    }

    private final long id;

    private final String name;

    private final String content;

    private final LocalDateTime createDate;

}
