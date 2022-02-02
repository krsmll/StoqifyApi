package com.knits.product.controller;

import java.util.List;
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

    @PutMapping(value = "/deactivate/{id}")
    public ResponseEntity<List<PurchaseOrderDto>> cancelPurchaseOrder(@PathVariable("id")Long purchaseOrderId) {
        purchaseOrderService.cancelPurchaseOrder(purchaseOrderId);
        return ResponseEntity.ok().body(purchaseOrderService.cancelPurchaseOrder(purchaseOrderId));
    }
}
