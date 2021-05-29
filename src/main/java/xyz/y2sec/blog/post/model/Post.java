package xyz.y2sec.blog.post.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import xyz.y2sec.blog.category.model.Category;
import xyz.y2sec.blog.comment.model.Comment;
import xyz.y2sec.blog.post.dto.PostDto;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    private long views;

    private LocalDateTime createDate;

    private LocalDateTime modifyDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private final List<Comment> comments = new ArrayList<>();

    public Post(PostDto postDto, Category category) {
        this.title = postDto.toString();
        this.content = postDto.toString();
        this.views = 0;

        LocalDateTime currentDateTime = LocalDateTime.now();
        this.createDate = currentDateTime;
        this.modifyDate = currentDateTime;

        this.category = category;
    }

    public void update(PostDto postDto, Category category) {
        this.title = postDto.toString();
        this.content = postDto.toString();
        this.modifyDate = LocalDateTime.now();
        this.category = category;
    }

}
