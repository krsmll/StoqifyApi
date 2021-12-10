package com.knits.product.dto;

import lombok.Data;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotBlank;

/**
 * This is a Data transfer object class
 * @author Soumen Banerjee
 */
@Data
public class TrailerDto {

    private Long id;

    @NotNull(message = "Brand name can not be null")
    @NotBlank(message = "Brand name can not be blank")
    private String brand;

    @NotBlank(message = "Model can not left blank")
    @NotNull(message = "Model can not be null")
    private String model;

    @NotNull(message = "Carrier name can not be null")
    @NotBlank(message = "Carrier name can not be blank")
    private String carrier;

    @NotBlank(message = "Licence number can not be blank")
    @NotNull(message = "Licence number can not be null")
    private String licenceNumber;
}
