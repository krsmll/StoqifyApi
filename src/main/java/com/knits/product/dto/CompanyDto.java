package com.knits.product.dto;

import lombok.Data;
import javax.validation.constraints.Pattern;

/**
 * This is a company Data Transfer Object to handle data
 * @author Soumen Banerjee
 */
@Data
public class CompanyDto {

    private Long id;

    @Pattern(regexp = "^[A-Za-z0-9]*$", message = "Registry code should contain only alpha numeric")
    private String registryCode;

    @Pattern(regexp = "^[A-Za-z0-9]*$", message = "Vat code should contain only alpha numeric")
    private String vatCode;

    @Pattern(regexp = "^[A-Za-z]*$", message = "Country code should contain only characters")
    private String countryCode;

    @Pattern(regexp = "^[A-Za-z]*$", message = "Country name should contain only characters")
    private String country;

    @Pattern(regexp = "^[A-Za-z]*$", message = "City name should contain only characters")
    private String city;

    @Pattern(regexp = "^[0-9]*$", message = "Zip code should contain only numbers")
    private Long zipCode;

    private String street;
    private String logo;
    private boolean active;
    private CompanyType companyType;
}
