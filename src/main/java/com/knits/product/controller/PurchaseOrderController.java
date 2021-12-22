package com.knits.product.controller;

import java.util.List;

import com.knits.product.entity.PurchaseOrder;
import lombok.AllArgsConstructor;
import com.knits.product.dto.PurchaseOrderDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.knits.product.service.PurchaseOrderService;

/**
 * This is a REST API controller to handle purchase order
 * @author Soumen Banerjee
 */
@RestController
@RequestMapping("/api/purchaseorder")
@AllArgsConstructor
public class PurchaseOrderController {

    private final PurchaseOrderService purchaseOrderService;

    @GetMapping("/all")
    public ResponseEntity<List<PurchaseOrderDto>> getAllPurchaseOrderList() {
        return ResponseEntity.ok().body(purchaseOrderService.getAllPurchaseOrderList());
    }

    @PostMapping
    public ResponseEntity<List<PurchaseOrderDto>> createPurchaseOrder(@RequestBody PurchaseOrderDto purchaseOrderDto) {
        return ResponseEntity.ok().body(purchaseOrderService.createPurchaseOrder(purchaseOrderDto));
    }
}
