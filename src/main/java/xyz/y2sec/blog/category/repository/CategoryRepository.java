package xyz.y2sec.blog.category.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.y2sec.blog.category.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
