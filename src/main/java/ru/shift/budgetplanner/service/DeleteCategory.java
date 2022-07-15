package ru.shift.budgetplanner.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.shift.budgetplanner.domain.Category;
import ru.shift.budgetplanner.domain.User;
import ru.shift.budgetplanner.dto.DeleteCategoryDto;
import ru.shift.budgetplanner.exception.CategoryException;
import ru.shift.budgetplanner.repository.CategoryRepository;

@Service
@RequiredArgsConstructor
public class DeleteCategory {
    private final GetCurrentUser getCurrentUser;
    private final CategoryService categoryService;

    public void exec(@NonNull DeleteCategoryDto deleteCategoryRequest){
        User user = getCurrentUser.exec();
        String categoryName = deleteCategoryRequest.getCategoryName();
        Category category = categoryService.getCategoryByNameAndOwnerNotNull(categoryName, user);
        categoryService.deleteCategory(category);
    }

}
