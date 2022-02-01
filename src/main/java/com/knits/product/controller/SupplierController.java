package com.knits.product.controller;

import com.knits.product.dto.SupplierDto;
import com.knits.product.service.SupplierService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * This is Customer REST API class
 *
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
        return new ResponseEntity<>(supplierService.registerSupplier(supplierDto), HttpStatus.CREATED);
    }
}
