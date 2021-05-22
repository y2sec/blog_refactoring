package xyz.y2sec.blog.category.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import xyz.y2sec.blog.category.dto.CategoryDto;
import xyz.y2sec.blog.post.model.Post;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private final List<Post> posts = new ArrayList<>();

    public Category(CategoryDto categoryDto) {
        this.name = categoryDto.getName();
    }

    public void update(CategoryDto categoryDto) {
        this.name = categoryDto.getName();
    }

}
