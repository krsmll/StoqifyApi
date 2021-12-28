package com.knits.product.dto;

import lombok.Data;

@Data
public class CustomerDto {

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
    private String companyType;
}
