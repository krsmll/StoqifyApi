package com.knits.product.dto;

import lombok.Data;

@Data
public class FacilityDto {

    private Long id;
    private String name;
    private String code;
    private String country;
    private String city;
    private Long zipCode;
    private String street;
    private String ingLtd;
    private Boolean status;
}
