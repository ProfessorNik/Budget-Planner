package ru.shift.budgetplanner.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.shift.budgetplanner.domain.Category;
import ru.shift.budgetplanner.domain.User;
import ru.shift.budgetplanner.dto.ChangeSpendingLimitDto;

@Service
@RequiredArgsConstructor
public class ChangeSpendingLimit {
    private final CategoryService categoryService;
    private final GetCurrentUser getCurrentUser;

    public void exec(@NonNull ChangeSpendingLimitDto changeSpendingLimitRequest){
        User owner = getCurrentUser.exec();
        Category category = categoryService.getCategoryByNameAndOwnerNotNull(changeSpendingLimitRequest.getCategoryName(), owner);
        category.setSpendingLimit(changeSpendingLimitRequest.getSpendingLimit());
        categoryService.saveCategory(category);
    }
}
