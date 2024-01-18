package io.srk.dictionary.repository.category;

import io.srk.dictionary.model.category.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
