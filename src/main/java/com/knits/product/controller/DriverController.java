package com.knits.product.controller;

import com.knits.product.dto.DriverDto;
import com.knits.product.service.DriverService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * This is a REST API controller class to handle all driver related requests
 *
 * @author Soumen Banerjee
 */
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/driver")
public class DriverController {

    private final DriverService driverService;

    /**
     * @return list of drivers
     */
    @GetMapping(value = "/all")
    private ResponseEntity<List<DriverDto>> getAllDrivers() {
        return ResponseEntity.ok().body(driverService.getAllDrivers());
    }

    /**
     * @param driverDto register a driver by requested data via driverDto
     * @return Driver
     */
    @PostMapping
    private ResponseEntity<DriverDto> saveDriver(@Valid @RequestBody DriverDto driverDto) {
        return new ResponseEntity<>(driverService.saveDriverData(driverDto), HttpStatus.CREATED);
    }

    /**
     * @param driverId requested driver id to fetch driver data
     * @return driver data by id
     */
    @GetMapping(value = "/{id}")
    public ResponseEntity<DriverDto> getDriverById(@PathVariable("id") Long driverId) {
        return ResponseEntity.ok().body(driverService.getDriverById(driverId));
    }

    /**
     * @param driverId requested driver id to delete driver data
     * @return void
     */
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteDriverId(@PathVariable("id") Long driverId) {
        driverService.deleteDriverById(driverId);
        return ResponseEntity.noContent().build();
    }

    /**
     * @param driverDto Driver DTO object.
     * @return List of drivers.
     */
    @PutMapping(value = "/update")
    public ResponseEntity<List<DriverDto>> updateDriverData(@Valid @RequestBody DriverDto driverDto) {
        return ResponseEntity.ok().body(driverService.getUpdatedDriverList(driverDto));
    }
}
