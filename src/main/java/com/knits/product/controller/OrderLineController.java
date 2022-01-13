package com.knits.product.controller;

import java.util.List;
import lombok.AllArgsConstructor;
import com.knits.product.dto.OrderLineDto;
import org.springframework.http.ResponseEntity;
import com.knits.product.service.OrderLineService;
import org.springframework.web.bind.annotation.*;

/**
 * This is a REST API controller class to handle Purchase Order data
 * @author Soumen Banerjee
 */
@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class OrderLineController {

    private final OrderLineService purchaseOrderService;

    @GetMapping("/orderline/all")
    public ResponseEntity<List<OrderLineDto>> getAllPurchaseOrder() {
        return ResponseEntity.ok().body(purchaseOrderService.getAllOrderLines());
    }

    @PutMapping("/orderline")
    public ResponseEntity<List<OrderLineDto>> editOrderLine(@RequestBody OrderLineDto purchaseOrderDto) {
        return ResponseEntity.ok().body(purchaseOrderService.editOrderLine(purchaseOrderDto));
    }

    @PutMapping("/orderline/deactivate/{id}")
    public ResponseEntity<List<OrderLineDto>> deactivateOrderLine(@PathVariable("id")Long orderLineId) {
        return ResponseEntity.ok().body(purchaseOrderService.deactivateOrderLine(orderLineId));
    }
}
