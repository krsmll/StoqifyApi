package com.knits.product.controller;

import com.knits.product.dto.CustomerDto;
import com.knits.product.service.CustomerService;
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
@RequestMapping("api/customers")
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping(value = "/all")
    public ResponseEntity<List<CustomerDto>> getAllCustomers() {
        return ResponseEntity.ok().body(customerService.getAllCustomers());
    }

    @PostMapping
    public ResponseEntity<List<CustomerDto>> registerNewCustomer(@RequestBody @Valid CustomerDto customerDto) {
        return new ResponseEntity<>(customerService.registerCustomer(customerDto), HttpStatus.CREATED);
    }
}
