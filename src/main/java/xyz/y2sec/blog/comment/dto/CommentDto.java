package xyz.y2sec.blog.comment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {

    public CommentDto(String name, String content, String password) {
        this.name = name;
        this.content = content;
        this.password = password;
    }

    private long id;

    @NotNull
    private String name;

    @NotNull
    private String content;

    @NotNull
    private String password;

    private long postId;
}
