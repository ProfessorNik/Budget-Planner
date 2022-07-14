package ru.shift.budgetplanner.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.shift.budgetplanner.dto.AddCategoryDto;
import ru.shift.budgetplanner.dto.DeleteCategoryDto;
import ru.shift.budgetplanner.service.CategoryService;

@RestController
@RequestMapping("api/category")
@RequiredArgsConstructor
@Log4j2
public class CategoryController {
    private final CategoryService service;

    @PostMapping("/add")
    void addCategory(@RequestBody AddCategoryDto addCategoryRequest){
        try {
            service.addCategory(addCategoryRequest);
        }catch (RuntimeException ex){
            log.error("category haven't added");
            ex.printStackTrace();
        }
    }

    @PostMapping("/del")
    void deleteCategory(@RequestBody DeleteCategoryDto deleteCategoryRequest){
        try{
            service.deleteCategory(deleteCategoryRequest);
        }catch (RuntimeException ex){
            log.error("category haven't deleted");
            ex.printStackTrace();
        }
    }
}
