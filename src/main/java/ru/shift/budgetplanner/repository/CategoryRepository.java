package ru.shift.budgetplanner.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.shift.budgetplanner.domain.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findCategoryByName(String name);
}
