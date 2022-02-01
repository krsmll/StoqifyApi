package com.knits.product.dto;

import lombok.Data;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * This is a Data transfer object class
 * @author Soumen Banerjee
 */
@Data
public class TrailerDto {
    private Long id;

    @NotNull(message = "Brand name can not be null")
    @NotBlank(message = "Brand name can not be blank")
    @Pattern(regexp = "^[A-Za-z]*$", message = "Brand name should contain only characters")
    private String brand;

    @NotBlank(message = "Model can not left blank")
    @NotNull(message = "Model can not be null")
    @Pattern(regexp = "^[A-Za-z]*$", message = "Model name should contain only characters")
    private String model;

    @NotNull(message = "Carrier name can not be null")
    @NotBlank(message = "Carrier name can not be blank")
    @Pattern(regexp = "^[A-Za-z]*$", message = "Carrier name should contain only characters")
    private String carrier;

    @NotBlank(message = "Licence number can not be blank")
    @NotNull(message = "Licence number can not be null")
    @Pattern(regexp = "^[A-Za-z0-9]*$", message = "Licence number should contain only alpha numeric")
    private String licenceNumber;
}
