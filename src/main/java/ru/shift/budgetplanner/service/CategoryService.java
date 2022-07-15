package ru.shift.budgetplanner.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.shift.budgetplanner.domain.Category;
import ru.shift.budgetplanner.domain.User;
import ru.shift.budgetplanner.exception.CategoryNotFoundException;
import ru.shift.budgetplanner.repository.CategoryRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public List<Category> getCategoriesByOwner(@NonNull User owner){
        return categoryRepository.findCategoriesByOwner(owner);
    }

    public Category saveCategory(@NonNull Category category){
        return categoryRepository.save(category);
    }

    public Category getCategoryByNameAndOwner(@NonNull String categoryName, @NonNull User owner){
        return categoryRepository.findCategoryByNameAndOwner(categoryName, owner);
    }

    public Category getCategoryByNameAndOwnerNotNull(@NonNull String categoryName, @NonNull User owner){
        Category category = getCategoryByNameAndOwner(categoryName, owner);
        if(category == null){
            throw new CategoryNotFoundException("category " + categoryName + " by " + owner.getUsername() + " isn't found");
        }
        return category;
    }

    public void deleteCategory(@NonNull Category category){
        categoryRepository.delete(category);
    }
}
