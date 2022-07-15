package ru.shift.budgetplanner.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.shift.budgetplanner.domain.Category;
import ru.shift.budgetplanner.domain.User;
import ru.shift.budgetplanner.dto.CategoryDto;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GetCategories {
    private final CategoryService categoryService;
    private final GetCurrentUser getCurrentUser;

    public List<CategoryDto> exec(){
        var owner = getCurrentUser.exec();
        var categories = categoryService.getCategoriesByOwner(owner);
        return mapToDto(categories);
    }

    public List<CategoryDto> mapToDto(@NonNull List<Category> categories){
        return categories.stream()
                .map(category -> new CategoryDto(category.getName(), category.getSpendingLimit()))
                .collect(Collectors.toList());
    }
}
