package xyz.y2sec.blog.category.service;

import xyz.y2sec.blog.category.dto.CategoryDto;
import xyz.y2sec.blog.category.model.Category;

import java.util.List;

public interface CategoryService {

    List<Category> findAll();

    Category findById(long categoryId);

    long addCategory(Category category);

    void modifyCategory(CategoryDto categoryDto, long categoryId);

    void removeCategory(long categoryId);

}
