package com.knits.product.controller;

import java.util.List;
import lombok.AllArgsConstructor;
import com.knits.product.dto.CompanyDto;
import org.springframework.http.ResponseEntity;
import com.knits.product.service.CompanyService;
import org.springframework.web.bind.annotation.*;

/**
 * This is a REST Controller to handle company related request
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
    public ResponseEntity<List<CompanyDto>> saveAndGetAllComapnies(@RequestBody CompanyDto companyDto) {
        return ResponseEntity.ok().body(companyService.getSavedCompanyWithOldCompanies(companyDto));
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
