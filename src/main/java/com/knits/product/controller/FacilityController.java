package com.knits.product.controller;

import java.util.List;
import lombok.AllArgsConstructor;
import com.knits.product.dto.FacilityDto;
import org.springframework.http.ResponseEntity;
import com.knits.product.service.FacilityService;
import org.springframework.web.bind.annotation.*;

/**
 * This is REST Controller to handle facility data
 * @author Soumen Banerjee
 */
@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class FacilityController {

    private final FacilityService facilityService;

    @GetMapping("/facility/all")
    public ResponseEntity<List<FacilityDto>> getAllFacilityData() {
        return ResponseEntity.ok().body(facilityService.getAllFacilityData());
    }

    @PostMapping("/facility")
    public ResponseEntity<List<FacilityDto>> saveFacilityData(@RequestBody FacilityDto facilityDto) {
        return ResponseEntity.ok().body(facilityService.saveFacilityData(facilityDto));
    }
}
