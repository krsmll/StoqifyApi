package com.knits.product.controller;

import java.util.List;
import lombok.AllArgsConstructor;
import com.knits.product.dto.PurchaseOrderDto;
import org.springframework.http.ResponseEntity;
import com.knits.product.service.PurchaseOrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This is a REST API controller class to handle Purchase Order data
 * @author Soumen Banerjee
 */

@RestController
@AllArgsConstructor
@RequestMapping("api/")
public class PurchaseOrderController {

    private final PurchaseOrderService purchaseOrderService;

    @GetMapping("purchases/all")
    public ResponseEntity<List<PurchaseOrderDto>> getAllPurchaseOrder() {
        return ResponseEntity.ok().body(purchaseOrderService.getAllPurchaseDtoList());
    }
}
