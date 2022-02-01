package com.knits.product.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * This is a Data transfer object class
 * @author Soumen Banerjee
 */
@Data
public class DriverDto {

    private Long id;

    @NotNull(message = "Driver first name can not be null")
    @NotNull(message = "Driver first name can not left blank")
    @Pattern(regexp = "^[A-Za-z]*$", message = "Driver first name should contain only characters")
    private String firstName;

    @NotNull(message = "Driver last name can not be null")
    @NotBlank(message = "Driver last name can not left blank")
    @Pattern(regexp = "^[A-Za-z]*$", message = "Driver last name should contain only characters")
    private String lastName;

    @NotNull(message = "Driver Licenece number can not be null")
    @NotBlank(message = "Driver Licence number can not be blank")
    @Pattern(regexp = "^[A-Za-z0-9]*$", message = "Driver licence number should contain only alpha numeric")
    private String drivingLicenceNumber;

    @NotNull(message = "Driver phone no can not be null")
    @NotBlank(message = "Driver phone no can not left blank")
    @Pattern(regexp = "^[0-9]*$", message = "Driver phone number should contain only numbers")
    private String phoneNo;

    @NotNull(message = "Driver address can not be null")
    @NotBlank(message = "Driver address can not left blank")
    @Pattern(regexp = "^[A-Za-z]*$", message = "Driver address should contain only characters")
    private String driverAddress;

    @NotNull(message = "Driver carrier name can not be null")
    @NotBlank(message = "Driver carrier name can not left blank")
    @Pattern(regexp = "^[A-Za-z]*$", message = "Carrier company name should contain only characters")
    private String carrierCompanyName;
}
