package ru.shift.budgetplanner.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.shift.budgetplanner.domain.Category;
import ru.shift.budgetplanner.domain.User;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findCategoriesByOwner(User owner);
    Category findCategoryByNameAndOwner(String categoryName, User owner);
    void deleteCategoryByNameAndOwner(String categoryName, User owner);
}
