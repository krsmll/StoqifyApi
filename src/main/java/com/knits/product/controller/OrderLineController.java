package com.knits.product.controller;

import com.knits.product.dto.OrderLineDto;
import com.knits.product.service.OrderLineService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This is a REST API controller class to handle Purchase Order data
 *
 * @author Soumen Banerjee
 */
@RestController
@AllArgsConstructor
@RequestMapping("/api/orderline")
public class OrderLineController {

    private final OrderLineService purchaseOrderService;

    @GetMapping(value = "/all")
    public ResponseEntity<List<OrderLineDto>> getAllPurchaseOrder() {
        return ResponseEntity.ok().body(purchaseOrderService.getAllOrderLines());
    }

    @PutMapping
    public ResponseEntity<List<OrderLineDto>> editOrderLine(@RequestBody OrderLineDto purchaseOrderDto) {
        return ResponseEntity.ok().body(purchaseOrderService.editOrderLine(purchaseOrderDto));
    }

    @PutMapping(value = "/deactivate/{id}")
    public ResponseEntity<List<OrderLineDto>> deactivateOrderLine(@PathVariable("id") Long orderLineId) {
        return ResponseEntity.ok().body(purchaseOrderService.deactivateOrderLine(orderLineId));
    }
}
