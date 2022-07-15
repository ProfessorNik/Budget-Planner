package ru.shift.budgetplanner.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.shift.budgetplanner.dto.*;
import ru.shift.budgetplanner.service.AddPurchase;
import ru.shift.budgetplanner.service.DeletePurchase;
import ru.shift.budgetplanner.service.ShowPurchaseService;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping("api/purchase")
public class PurchaseController {
    private final DeletePurchase deletePurchase;
    private final AddPurchase addPurchase;
    private final ShowPurchaseService showPurchaseService;

    @GetMapping
    public ResponseEntity<List<PurchaseDto>>
    getPurchaseForTheLastMonthByCategory(@RequestBody GetPurchasesByCategoryDto getPurchasesByCategoryRequest){
        try {
            var purchases = showPurchaseService.getPurchasesDtoForTheLastMonthByCategory(getPurchasesByCategoryRequest);
            return ResponseEntity.ok(purchases);
        }catch (RuntimeException ex){
            log.error("Purchases can't output", ex);
            return new ResponseEntity<>(HttpStatusCode.valueOf(400));
        }
    }

    @GetMapping("/stat")
    public ResponseEntity<SpendingMoneyAndLimitDto>
    get(@RequestBody GetPurchasesByCategoryDto getPurchasesByCategoryRequest){
        try {
            var costAndLimit = showPurchaseService.getCostAndLimitForTheLastMonthByCategory(getPurchasesByCategoryRequest);
            return ResponseEntity.ok(costAndLimit);
        } catch (RuntimeException ex){
            log.error("Cost and limit can't output", ex);
            return new ResponseEntity<>(HttpStatusCode.valueOf(400));
        }
    }

    @PostMapping
    public ResponseEntity<String> addPurchase(@RequestBody AddPurchaseDto addPurchaseRequest){
        try {
            addPurchase.exec(addPurchaseRequest);
            return ResponseEntity.ok("Purchase successfully added");
        }catch(RuntimeException ex){
            log.error("purchase haven't added", ex);
            return ResponseEntity.of(Optional.of("Sorry, purchase haven't added"));
        }
    }

    @DeleteMapping
    public ResponseEntity<String> deletePurchase(@RequestBody DeletePurchaseDto deletePurchaseRequest){
        try {
            deletePurchase.exec(deletePurchaseRequest);
            return ResponseEntity.ok("Purchase successfully deleted");
        }catch(RuntimeException ex){
            log.error("purchase haven't deleted", ex);
            return ResponseEntity.of(Optional.of("Sorry, purchase haven't deleted"));
        }
    }
}
