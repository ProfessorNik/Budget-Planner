package ru.shift.budgetplanner.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.shift.budgetplanner.dto.AddCategoryDto;
import ru.shift.budgetplanner.dto.CategoryDto;
import ru.shift.budgetplanner.dto.ChangeSpendingLimitDto;
import ru.shift.budgetplanner.dto.DeleteCategoryDto;
import ru.shift.budgetplanner.service.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/category")
@RequiredArgsConstructor
@Log4j2
public class CategoryController {
    private final AddCategory addCategory;
    private final DeleteCategory deleteCategory;
    private final ChangeSpendingLimit changeSpendingLimit;
    private final GetCategories getCategories;


    @PostMapping("/add")
    public ResponseEntity<String> addCategory(@RequestBody AddCategoryDto addCategoryRequest){
        try {
            addCategory.exec(addCategoryRequest);
            return ResponseEntity.ok("Category " + addCategoryRequest.getCategoryName() + "successfully added");
        }catch (RuntimeException ex){
            log.error("category haven't added ", ex);
            return ResponseEntity.of(Optional.of("Sorry, category haven't added"));
        }
    }

    @PostMapping("/del")
    public ResponseEntity<String> deleteCategory(@RequestBody DeleteCategoryDto deleteCategoryRequest){
        try{
            deleteCategory.exec(deleteCategoryRequest);
            return ResponseEntity.ok("Category " + deleteCategoryRequest.getCategoryName() + "successfully deleted");
        }catch (RuntimeException ex){
            log.error("category haven't deleted", ex);
            return ResponseEntity.of(Optional.of("Sorry, category haven't added"));
        }
    }

    @GetMapping("/show")
    public List<CategoryDto> showCategories(){
        return getCategories.exec();
    }
    
    @PostMapping("/change")
    public ResponseEntity<String> changeCategory(@RequestBody ChangeSpendingLimitDto changeSpendingLimitDto) {
        try {
            changeSpendingLimit.exec(changeSpendingLimitDto);
            return ResponseEntity.ok("Category " + changeSpendingLimitDto.getCategoryName() + " successfully changed");
        }catch(RuntimeException ex){
            log.error("category haven't changed", ex);
            return ResponseEntity.of(Optional.of("Sorry, category haven't added"));
        }
    }
}
