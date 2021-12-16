package com.knits.product.controller;

import java.util.List;
import lombok.AllArgsConstructor;
import com.knits.product.dto.PurchaseOrderDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.knits.product.service.PurchasrOrderService;

/**
 * This is a REST API controller to handle purchase order
 * @author Soumen Banerjee
 */
@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class PurchaseOrderController {

    private final PurchasrOrderService purchasrOrderService;

    @GetMapping("/purchaseorder/all")
    public ResponseEntity<List<PurchaseOrderDto>> getAllPurchaseOrderList() {
        return ResponseEntity.ok().body(purchasrOrderService.getAllPurchaseOrderList());
    }

    @PostMapping("/purchaseorder/create")
    public  ResponseEntity<List<PurchaseOrderDto>> createPurchaseOrder(@RequestBody PurchaseOrderDto purchaseOrderDto) {
        return ResponseEntity.ok().body(purchasrOrderService.createPurchaseOrder(purchaseOrderDto));
    }
}
