package ru.shift.budgetplanner.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.shift.budgetplanner.domain.Category;
import ru.shift.budgetplanner.domain.Purchase;
import ru.shift.budgetplanner.domain.User;
import ru.shift.budgetplanner.dto.GetPurchasesByCategoryDto;
import ru.shift.budgetplanner.dto.PurchaseDto;
import ru.shift.budgetplanner.dto.SpendingMoneyAndLimitDto;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShowPurchaseService {
    private final GetCurrentUser getCurrentUser;
    private final PurchaseService purchaseService;
    private final CategoryService categoryService;

    public SpendingMoneyAndLimitDto getCostAndLimitForTheLastMonthByCategory(@NonNull GetPurchasesByCategoryDto getPurchasesByCategoryRequest){
        User buyer = getCurrentUser.exec();
        var category = categoryService.getCategoryByNameAndOwner(getPurchasesByCategoryRequest.getCategoryName(), buyer);
        var spendingLimit = category.getSpendingLimit();
        var costForTheMonth = getCostForTheLastMonthByCategory(getPurchasesByCategoryRequest);
        return new SpendingMoneyAndLimitDto(costForTheMonth, spendingLimit);
    }

    public BigDecimal getCostForTheLastMonthByCategory(@NonNull GetPurchasesByCategoryDto getPurchasesByCategoryRequest){
        var purchases = getPurchasesForTheLastMonthByCategory(getPurchasesByCategoryRequest);
        BigDecimal fullCost = BigDecimal.ZERO;
        for(var purchase : purchases){
            fullCost = fullCost.add(purchase.getCost());
        }
        return fullCost;
    }

    public List<PurchaseDto>
    getPurchasesDtoForTheLastMonthByCategory(@NonNull GetPurchasesByCategoryDto getPurchasesByCategoryRequest){
        var purchases = getPurchasesForTheLastMonthByCategory(getPurchasesByCategoryRequest);
        return mapToDto(purchases);
    }

    public List<Purchase> getPurchasesForTheLastMonthByCategory(@NonNull GetPurchasesByCategoryDto getPurchasesByCategoryRequest){
        var purchases = getPurchasesByCategory(getPurchasesByCategoryRequest);
        return getPurchasesForTheLastMonth(purchases);
    }
    public List<Purchase> getPurchasesByCategory(@NonNull GetPurchasesByCategoryDto getPurchasesByCategoryRequest){
        User buyer = getCurrentUser.exec();
        String categoryName = getPurchasesByCategoryRequest.getCategoryName();
        Category category = categoryService.getCategoryByNameAndOwnerNotNull(categoryName, buyer);
        return purchaseService.getPurchasesByCategoryAndBuyer(category, buyer);
    }
    private List<PurchaseDto> mapToDto(@NonNull List<Purchase> purchases){
        return purchases.stream()
                .map(purchase ->
                        new PurchaseDto(purchase.getId(),
                                purchase.getCost(),
                                purchase.getCategory().getName()))
                .collect(Collectors.toList());
    }

    public List<Purchase> getPurchasesForTheLastMonth(@NonNull List<Purchase> purchases){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 0);
        var dateBegin = calendar.getTime();
        return getPurchaseForTheMonth(purchases, dateBegin);
    }

    private List<Purchase> getPurchaseForTheMonth(@NonNull List<Purchase> purchases, @NonNull Date dateBegin){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateBegin);
        calendar.add(Calendar.MONTH, 1);
        Date dateEnd = calendar.getTime();
        return getPurchaseForTheRange(purchases, dateBegin, dateEnd);
    }

    private List<Purchase> getPurchaseForTheRange(@NonNull List<Purchase> purchases, @NonNull Date dateBegin,@NonNull Date dateEnd){
        return purchases.stream().filter(purchase -> {
            Date addedDate = new Date(purchase.getAddedTime());
            return dateBegin.before(addedDate) && dateEnd.after(addedDate);
        }).collect(Collectors.toList());
    }
}
