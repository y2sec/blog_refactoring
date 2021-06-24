package xyz.y2sec.blog.comment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CommentDto {

    private String name;
    private String content;
    private String password;

}
