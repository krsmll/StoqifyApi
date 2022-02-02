package com.knits.product.controller;

import com.knits.product.dto.CompanyDto;
import com.knits.product.service.CompanyService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This is a REST Controller to handle company related request
 *
 * @author Soumen Banerjee
 */
@RestController
@AllArgsConstructor
@RequestMapping("/api/company")
public class CompanyController {

    private final CompanyService companyService;

    @GetMapping(value = "/all")
    public ResponseEntity<List<CompanyDto>> getAllCompanies() {
        return ResponseEntity.ok().body(companyService.getAllCompanies());
    }

    @PostMapping
    public ResponseEntity<List<CompanyDto>> saveAndGetAllCompanies(@RequestBody CompanyDto companyDto) {
        return new ResponseEntity<>(companyService.getSavedCompanyWithOldCompanies(companyDto), HttpStatus.CREATED);
    }

    @PutMapping(value = "/disable")
    public ResponseEntity<Void> deactivateCompany(@RequestBody CompanyDto companyDto) {
        companyService.deactivateCompany(companyDto);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping(value = "/edit")
    public ResponseEntity<List<CompanyDto>> editCompany(@RequestBody CompanyDto companyDto) {
        return ResponseEntity.ok().body(companyService.getCompanyListAfterEdit(companyDto));
    }

    @GetMapping(value = "/search/{company}")
    public ResponseEntity<CompanyDto> searchCompany(@PathVariable("company") String companyName) {
        return ResponseEntity.ok().body(companyService.getCompanyBySearchedName(companyName));
    }
}
