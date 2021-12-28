package com.knits.product.controller;

import java.util.List;
import lombok.AllArgsConstructor;
import com.knits.product.dto.CustomerDto;
import org.springframework.http.ResponseEntity;
import com.knits.product.service.CustomerService;
import org.springframework.web.bind.annotation.*;

/**
 * This is Customer REST API class
 */
@RestController
@RequestMapping("api/customers")
@AllArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/all")
    public ResponseEntity<List<CustomerDto>> getAllCustomers() {
        return ResponseEntity.ok().body(customerService.getAllCustomers());
    }

    @PostMapping("/create")
    public ResponseEntity<List<CustomerDto>> registerNewCustomer(@RequestBody CustomerDto customerDto) {
        return ResponseEntity.ok().body(customerService.registerCustomer(customerDto));
    }
}
