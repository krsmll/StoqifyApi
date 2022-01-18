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
@RequestMapping("/api/facility")
public class FacilityController {

    private final FacilityService facilityService;

    @GetMapping(value = "/all")
    public ResponseEntity<List<FacilityDto>> getAllFacilityData() {
        return ResponseEntity.ok().body(facilityService.getAllFacilityData());
    }

    @PostMapping
    public ResponseEntity<List<FacilityDto>> saveFacilityData(@RequestBody FacilityDto facilityDto) {
        return ResponseEntity.ok().body(facilityService.saveFacilityData(facilityDto));
    }

    @PutMapping(value = "/edit")
    public ResponseEntity<List<FacilityDto>> editFacilityData(@RequestBody FacilityDto facilityDto) {
        return ResponseEntity.ok().body(facilityService.editFacilityData(facilityDto));
    }

    @GetMapping(value = "/{searchedword}")
    public ResponseEntity<FacilityDto> searchFacilityData(@PathVariable("searchedword")String facilitySearchWord) {
        return ResponseEntity.ok().body(facilityService.getFacilitySearchedData(facilitySearchWord));
    }

    @PatchMapping(value = "/deactivate")
    public ResponseEntity<List<FacilityDto>> searchedFailcityData(@RequestBody FacilityDto facilityDto) {
        return ResponseEntity.ok().body(facilityService.deactivateFacilityData(facilityDto));
    }
}
