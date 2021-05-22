package xyz.y2sec.blog.category.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.y2sec.blog.category.dto.CategoryDto;
import xyz.y2sec.blog.category.model.Category;
import xyz.y2sec.blog.category.repository.CategoryRepository;
import xyz.y2sec.blog.category.service.CategoryService;

import javax.persistence.EntityNotFoundException;
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
    public Category findById(long categoryId) {
        Optional<Category> findCategory = categoryRepository.findById(categoryId);

        if (findCategory.isPresent()) {
            return findCategory.get();
        } else {
            throw new EntityNotFoundException();
        }
    }

    @Override
    public long addCategory(Category category) {
        Category saveCategory = categoryRepository.save(category);
        return saveCategory.getId();
    }

    @Override
    public void modifyCategory(CategoryDto categoryDto, long categoryId) {
        Optional<Category> findCategory = categoryRepository.findById(categoryId);

        if (findCategory.isPresent()) {
            findCategory.get().update(categoryDto);
        } else {
            throw new EntityNotFoundException();
        }
    }

    @Override
    public void removeCategory(long categoryId) {
        Optional<Category> findCategory = categoryRepository.findById(categoryId);

        if (findCategory.isPresent()) {
            categoryRepository.delete(findCategory.get());
        } else {
            throw new EntityNotFoundException();
        }
    }
}
