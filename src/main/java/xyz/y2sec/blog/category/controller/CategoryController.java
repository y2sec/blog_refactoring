package xyz.y2sec.blog.category.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.y2sec.blog.category.dto.CategoryDto;
import xyz.y2sec.blog.category.dto.CategoryResponse;
import xyz.y2sec.blog.category.model.Category;
import xyz.y2sec.blog.category.service.CategoryService;
import xyz.y2sec.blog.common.dto.ApiResult;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "api/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<ApiResult> getCategories() {
        List<Category> categories = categoryService.findAll();

        List<CategoryResponse> categoriesResponse = categories
                .stream()
                .map(CategoryResponse::new)
                .collect(Collectors.toList());

        return new ResponseEntity<>(ApiResult
                .builder()
                .data(categoriesResponse)
                .message("success")
                .build(), HttpStatus.OK);
    }

    @GetMapping(path = "/{categoryId}")
    public ResponseEntity<ApiResult> getCategoryById(@PathVariable(name = "categoryId") long categoryId) {
        Category findCategory = categoryService.findById(categoryId);

        return new ResponseEntity<>(ApiResult
                .builder()
                .data(new CategoryResponse(findCategory))
                .message("success")
                .build(), HttpStatus.OK);
    }

    @PostMapping(path = "/add")
    public ResponseEntity<ApiResult> createCategory(@RequestBody @Valid CategoryDto categoryDto) {
        long categoryId = categoryService.addCategory(new Category(categoryDto));

        Category createCategory = categoryService.findById(categoryId);

        return new ResponseEntity<>(ApiResult
                .builder()
                .data(new CategoryResponse(createCategory))
                .message("success")
                .build(), HttpStatus.OK);
    }

    @PutMapping(path = "/modify/{categoryId}")
    public ResponseEntity<ApiResult> modifyCategory(@RequestBody @Valid CategoryDto categoryDto, @PathVariable(name = "categoryId") long categoryId) {
        categoryService.modifyCategory(categoryDto, categoryId);

        Category modifyCategory = categoryService.findById(categoryId);

        return new ResponseEntity<>(ApiResult
                .builder()
                .data(new CategoryResponse(modifyCategory))
                .message("success")
                .build(), HttpStatus.OK);
    }

    @DeleteMapping(path = "/delete/{categoryId}")
    public ResponseEntity<ApiResult> deleteCategory(@PathVariable(name = "categoryId") long categoryId) {
        categoryService.removeCategory(categoryId);

        return new ResponseEntity<>(ApiResult
                .builder()
                .data(null)
                .message("success")
                .build(), HttpStatus.OK);
    }

}
