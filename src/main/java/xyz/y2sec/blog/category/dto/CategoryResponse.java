package xyz.y2sec.blog.category.dto;

import lombok.Getter;
import xyz.y2sec.blog.category.model.Category;

@Getter
public class CategoryResponse {

    public CategoryResponse(Category category) {
        this.id = category.getId();
        this.name = category.getName();
        this.size = category.getPosts().size();
    }

    private final long id;

    private final String name;

    private final int size;

}
