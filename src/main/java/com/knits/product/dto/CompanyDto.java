package com.knits.product.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This is a company Data Transfer Object to handle data
 * @author Soumen Banerjee
 */
@Data
@NoArgsConstructor
public class CompanyDto {

    private Long id;
    private String registryCode;
    private String vatCode;
    private String countryCode;
    private String country;
    private String city;
    private Long zipCode;
    private String street;
    private String logo;
    private boolean active;
    private CompanyType companyType;
}
