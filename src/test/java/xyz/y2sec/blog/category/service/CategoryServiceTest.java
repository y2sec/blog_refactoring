package xyz.y2sec.blog.category.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import xyz.y2sec.blog.category.dto.CategoryDto;
import xyz.y2sec.blog.category.model.Category;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import java.util.List;

@SpringBootTest
@Transactional
class CategoryServiceTest {

    @Autowired
    private CategoryService categoryService;

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    @DisplayName("카테고리 추가")
    void addCategory() {
        // given
        Category category = new Category();
        long categoryId = categoryService.addCategory(category);

        // when
        Category findCategory = categoryService.findById(categoryId);

        // then
        Assertions.assertEquals(findCategory, category);
    }

    @Test
    @DisplayName("카테고리 조회")
    void findCategory() {
        // given
        Category category1 = new Category();
        Category category2 = new Category();
        Category category3 = new Category();

        categoryService.addCategory(category1);
        categoryService.addCategory(category2);
        categoryService.addCategory(category3);

        // when
        List<Category> categories = categoryService.findAll();

        // then
        Assertions.assertEquals(categories.size(), 3);
    }

    @Test
    @DisplayName("카테고리 수정")
    void updateCategory() {
        // given
        CategoryDto beforeCategoryDto = new CategoryDto("이전");
        Category category = new Category(beforeCategoryDto);
        long categoryId = categoryService.addCategory(category);

        // when
        CategoryDto afterCategoryDto = new CategoryDto("이후");
        categoryService.modifyCategory(afterCategoryDto, categoryId);
        entityManager.flush();
        entityManager.clear();

        Category findCategory = categoryService.findById(categoryId);

        // then
        Assertions.assertEquals(findCategory.getName(), "이후");
    }

    @Test
    @DisplayName("카테고리 삭제")
    void deleteCategory() {
        // given
        Category category = new Category();
        long categoryId = categoryService.addCategory(category);

        // when
        categoryService.removeCategory(categoryId);

        // then
        Assertions.assertThrows(EntityNotFoundException.class, () -> categoryService.findById(categoryId));
    }

}