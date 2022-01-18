package com.knits.product.controller;

import java.util.List;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import com.knits.product.dto.CustomerDto;
import org.springframework.http.ResponseEntity;
import com.knits.product.service.CustomerService;
import org.springframework.web.bind.annotation.*;

/**
 * This is Customer REST API class
 * @author Soumen Banerjee
 */
@RestController
@AllArgsConstructor
@RequestMapping("api/customers")
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping(value = "/all")
    public ResponseEntity<List<CustomerDto>> getAllCustomers() {
        return ResponseEntity.ok().body(customerService.getAllCustomers());
    }

    @PostMapping
    public ResponseEntity<List<CustomerDto>> registerNewCustomer(@RequestBody @Valid CustomerDto customerDto) {
        return ResponseEntity.ok().body(customerService.registerCustomer(customerDto));
    }
}
