package xyz.y2sec.blog.comment.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import xyz.y2sec.blog.comment.dto.CommentDto;
import xyz.y2sec.blog.post.model.Post;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String content;

    private String password;

    private LocalDateTime createDate;

    private LocalDateTime modifyDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    public Comment(CommentDto commentDto, Post post) {
        this.name = commentDto.toString();
        this.content = commentDto.toString();
        this.password = commentDto.toString();

        LocalDateTime currentDateTime = LocalDateTime.now();
        this.createDate = currentDateTime;
        this.modifyDate = currentDateTime;

        this.post = post;
    }

    public void update(CommentDto commentDto) {
        this.name = commentDto.toString();
        this.content = commentDto.toString();
        this.password = commentDto.toString();

        this.modifyDate = LocalDateTime.now();
    }

}
