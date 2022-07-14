package ru.shift.budgetplanner.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import ru.shift.budgetplanner.domain.Category;
import ru.shift.budgetplanner.domain.User;
import ru.shift.budgetplanner.dto.AddCategoryDto;
import ru.shift.budgetplanner.dto.DeleteCategoryDto;
import ru.shift.budgetplanner.exception.CategoryException;
import ru.shift.budgetplanner.repository.CategoryRepository;
import ru.shift.budgetplanner.repository.UserRepository;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
@Log4j2
public class CategoryService {
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final GetCurrentUser getCurrentUser;

    public void addCategory(@NonNull AddCategoryDto addCategoryRequest){
        User user = getCurrentUser.exec();
        String categoryName = addCategoryRequest.getCategoryName();
        Category category = createAndSaveOrGetCategoryByName(categoryName);
        addCategoryToUser(category, user);
    }

    public void deleteCategory(@NonNull DeleteCategoryDto deleteCategoryRequest){
        User user = getCurrentUser.exec();
        String categoryName = deleteCategoryRequest.getCategoryName();
        Category category = getCategoryByName(categoryName);
        if(category == null)
            throw new CategoryException("category not found");
        deleteCategoryFromUser(category, user);
    }

    private void deleteCategoryFromUser(@NonNull Category category, @NonNull User user){
        if(!hasUserThisCategory(user, category)){
            throw new CategoryException("user haven't this category");
        }
        var userCategories = user.getCategories();
        userCategories.remove(category);
        user.setCategories(userCategories);
        userRepository.save(user);
    }


    private Category createAndSaveOrGetCategoryByName(@NonNull String categoryName){
        Category category = getCategoryByName(categoryName);
        if(category == null){
            category = createAndSaveCategory(categoryName);
        }
        return category;
    }

    private Category createAndSaveCategory(@NonNull String categoryName){
        Category newCategory = createCategory(categoryName);
        newCategory = saveCategory(newCategory);
        return newCategory;
    }
    private Category createCategory(@NonNull String categoryName) {
        Category newCategory = new Category();
        newCategory.setName(categoryName);
        newCategory.setSpendingLimit(BigDecimal.ZERO);
        return newCategory;
    }

    private Category saveCategory(@NonNull Category category){
        return categoryRepository.save(category);
    }

    private Category getCategoryByName(@NonNull String categoryName){
        return categoryRepository.findCategoryByName(categoryName);
    }

    private void addCategoryToUser(@NonNull Category category, @NonNull User user){
        if(hasUserThisCategory(user, category)){
            log.warn(user.getUsername() + " already has category " + category.getName());
            throw new CategoryException("user already has this category");
        }
        var userCategories = user.getCategories();
        userCategories.add(category);
        user.setCategories(userCategories);
        userRepository.save(user);
    }


    private boolean hasUserThisCategory(@NonNull User user, @NonNull Category category){
        var userCategories = user.getCategories();
        for(var userCategory: userCategories){
            if(userCategory.getName().equals(category.getName())){
                return true;
            }
        }
        return false;
    }
}
