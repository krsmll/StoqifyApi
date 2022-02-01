package com.knits.product.controller;

import com.knits.product.dto.CarrierDto;
import com.knits.product.service.CarrierService;
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
@RequestMapping("api/carrier")
public class CarrierController {

    private final CarrierService carrierService;

    @GetMapping(value = "/all")
    public ResponseEntity<List<CarrierDto>> getAllCarrier() {
        return ResponseEntity.ok().body(carrierService.getAllCarrier());
    }

    @PostMapping
    public ResponseEntity<List<CarrierDto>> registerNewCarrier(@RequestBody @Valid CarrierDto carrierDto) {
        return new ResponseEntity<>(carrierService.registerCarrier(carrierDto), HttpStatus.CREATED);
    }
}
