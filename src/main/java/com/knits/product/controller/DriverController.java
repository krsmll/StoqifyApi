package com.knits.product.controller;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.knits.product.dto.DriverDto;
import com.knits.product.service.DriverService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/driver")
public class DriverController {

    private final DriverService driverService;

    @GetMapping(value = "/all")
    private ResponseEntity<List<DriverDto>> getAllDrivers() {
        return ResponseEntity.ok().body(driverService.getAllDrivers());
    }

    @PostMapping(value = "/register")
    private ResponseEntity<Void> saveDriver(@Validated() @RequestBody DriverDto driverDto) {
        driverService.saveDriverData(driverDto);
        return ResponseEntity.noContent().build();
    }
}
