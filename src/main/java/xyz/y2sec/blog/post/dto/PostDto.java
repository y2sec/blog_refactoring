package xyz.y2sec.blog.post.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {

    public PostDto(String title, String content, long categoryId) {
        this.title = title;
        this.content = content;
        this.categoryId = categoryId;
    }

    private long id;

    @NotNull
    private String title;

    @NotNull
    private String content;

    private long categoryId;

}
