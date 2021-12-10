package com.knits.product.controller;

import java.util.List;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.knits.product.dto.DriverDto;
import com.knits.product.service.DriverService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * This is a REST API controller class to handle all driver related requests
 * @author Soumen Banerjee
 */
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class DriverController {

    private final DriverService driverService;

    /**
     *
     * @return list of drivers
     */
    @GetMapping(value = "/driver/all")
    private ResponseEntity<List<DriverDto>> getAllDrivers() {
        return ResponseEntity.ok().body(driverService.getAllDrivers());
    }

    /**
     *
     * @param driverDto register a driver by requested data via driverDto
     * @return void
     */
    @PostMapping(value = "/driver/register")
    private ResponseEntity<Void> saveDriver(@Valid @RequestBody DriverDto driverDto) {
        driverService.saveDriverData(driverDto);
        return ResponseEntity.noContent().build();
    }

    /**
     *
     * @param driverId requested driver id to fetch driver data
     * @return driver data by id
     */
    @GetMapping("/driver/{id}")
    public ResponseEntity<DriverDto> getDriverById(@PathVariable("id")Long driverId) {
        return ResponseEntity.ok().body(driverService.getDriverById(driverId));
    }

    /**
     *
     * @param driverId requested driver id to delete driver data
     * @return void
     */
    @DeleteMapping("/driver/{id}")
    public ResponseEntity<Void> deleteDriverId(@PathVariable("id")Long driverId) {
        driverService.deleteDriverById(driverId);
        return ResponseEntity.noContent().build();
    }

    /**
     *
     * @param driverDto
     * @return
     */
    @PutMapping("/driver/update")
    public ResponseEntity<List<DriverDto>> updateDriverData(@Valid @RequestBody DriverDto driverDto) {
        return ResponseEntity.ok().body(driverService.getUpdatedDriverList(driverDto));
    }
}
