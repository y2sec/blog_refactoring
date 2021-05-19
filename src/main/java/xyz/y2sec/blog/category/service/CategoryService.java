package xyz.y2sec.blog.category.service;

import xyz.y2sec.blog.category.dto.CategoryDto;
import xyz.y2sec.blog.category.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    List<Category> findAll();

    Optional<Category> findById(long categoryId);

    long addCategory(Category category);

    void modifyCategory(CategoryDto categoryDto, long categoryId);

    void removeCategory(long categoryId);

}
