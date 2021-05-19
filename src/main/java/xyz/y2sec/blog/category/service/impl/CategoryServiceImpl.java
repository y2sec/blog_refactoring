package xyz.y2sec.blog.category.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.y2sec.blog.category.dto.CategoryDto;
import xyz.y2sec.blog.category.model.Category;
import xyz.y2sec.blog.category.repository.CategoryRepository;
import xyz.y2sec.blog.category.service.CategoryService;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Category> findById(long categoryId) {
        return categoryRepository.findById(categoryId);
    }

    @Override
    public long addCategory(Category category) {
        Category saveCategory = categoryRepository.save(category);
        return saveCategory.getId();
    }

    @Override
    public void modifyCategory(CategoryDto categoryDto, long categoryId) {
        Category category = categoryRepository.getOne(categoryId);
        // TODO = Update 구현하기
    }

    @Override
    public void removeCategory(long categoryId) {
        Category category = categoryRepository.getOne(categoryId);
        categoryRepository.delete(category);
    }
}
