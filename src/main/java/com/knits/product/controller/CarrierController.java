package com.knits.product.controller;

import java.util.List;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import com.knits.product.dto.CarrierDto;
import com.knits.product.service.CarrierService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * This is Customer REST API class
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
        return ResponseEntity.ok().body(carrierService.registerCarrier(carrierDto));
    }
}
