package ru.shift.budgetplanner.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.shift.budgetplanner.dto.DeletePurchaseDto;

@Service
@RequiredArgsConstructor
public class DeletePurchase {
    private final PurchaseService purchaseService;

    public void exec(@NonNull DeletePurchaseDto deletePurchaseRequest){
        Long purchaseId = deletePurchaseRequest.getPurchaseId();
        purchaseService.deletePurchaseById(purchaseId);
    }
}
