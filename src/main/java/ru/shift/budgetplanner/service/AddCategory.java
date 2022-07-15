package ru.shift.budgetplanner.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.shift.budgetplanner.domain.Category;
import ru.shift.budgetplanner.domain.User;
import ru.shift.budgetplanner.dto.AddCategoryDto;
import ru.shift.budgetplanner.exception.CategoryAlreadyExistException;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class AddCategory {
    private final CategoryService categoryService;
    private final GetCurrentUser getCurrentUser;

    public void exec(@NonNull AddCategoryDto addCategoryRequest){
        User user = getCurrentUser.exec();
        String categoryName = addCategoryRequest.getCategoryName();
        createAndSaveCategory(categoryName, user);
    }

    private void createAndSaveCategory(@NonNull String categoryName, @NonNull User owner){
        if(categoryService.getCategoryByNameAndOwner(categoryName, owner) != null) {
            throw new CategoryAlreadyExistException("category with name " +  categoryName + "  already exist");
        }
        Category newCategory = createCategory(categoryName, owner);
        categoryService.saveCategory(newCategory);
    }
    private Category createCategory(@NonNull String categoryName, @NonNull User owner) {
        Category newCategory = new Category();
        newCategory.setName(categoryName);
        newCategory.setSpendingLimit(BigDecimal.ZERO);
        newCategory.setOwner(owner);
        return newCategory;
    }

}
