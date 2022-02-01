package com.knits.product.controller;

import com.knits.product.dto.PurchaseOrderDto;
import com.knits.product.service.PurchaseOrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This is a REST API controller to handle purchase order
 *
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
        return new ResponseEntity<>(purchaseOrderService.createPurchaseOrder(purchaseOrderDto), HttpStatus.CREATED);
    }

    @PutMapping(value = "/deactivate/{id}")
    public ResponseEntity<List<PurchaseOrderDto>> cancelPurchaseOrder(@PathVariable("id") Long purchaseOrderId) {
        purchaseOrderService.cancelPurchaseOrder(purchaseOrderId);
        return ResponseEntity.ok().body(purchaseOrderService.cancelPurchaseOrder(purchaseOrderId));
    }
}
