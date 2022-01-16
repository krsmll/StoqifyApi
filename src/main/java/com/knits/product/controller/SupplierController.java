package com.knits.product.controller;

import java.util.List;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import com.knits.product.dto.SupplierDto;
import org.springframework.http.ResponseEntity;
import com.knits.product.service.SupplierService;
import org.springframework.web.bind.annotation.*;

/**
 * This is Customer REST API class
 * @author Soumen Banerjee
 */
@RestController
@AllArgsConstructor
@RequestMapping("api/supplier")
public class SupplierController {

    private final SupplierService supplierService;

    @GetMapping
    public ResponseEntity<List<SupplierDto>> getAllSuppliers() {
        return ResponseEntity.ok().body(supplierService.getAllSuppliers());
    }

    @PostMapping
    public ResponseEntity<List<SupplierDto>> registerNewSupplier(@RequestBody @Valid SupplierDto supplierDto) {
        return ResponseEntity.ok().body(supplierService.registerSupplier(supplierDto));
    }
}
