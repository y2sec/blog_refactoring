package xyz.y2sec.blog.post.dto;

import lombok.Getter;
import xyz.y2sec.blog.post.model.Post;

@Getter
public class PostResponse {

    public PostResponse(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
    }

    private final long id;

    private final String title;

    private final String content;
}
