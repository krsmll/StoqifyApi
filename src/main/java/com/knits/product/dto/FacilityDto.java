package com.knits.product.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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
    private List<UserDto> users;
}
