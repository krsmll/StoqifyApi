package com.knits.product.dto;

import lombok.Data;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotBlank;

/**
 * This is a Data transfer object class
 * @author Soumen Banerjee
 */
@Data
public class DriverDto {

    private Long id;

    @NotNull(message = "Driver first name can not be null")
    @NotNull(message = "Driver first name can not left blank")
    private String firstName;

    @NotNull(message = "Driver last name can not be null")
    @NotBlank(message = "Driver last name can not left blank")
    private String lastName;

    @NotNull(message = "Driver Licenece number can not be null")
    @NotBlank(message = "Driver Licence number can not be blank")
    private String drivingLicenceNumber;

    @NotNull(message = "Driver phone no can not be null")
    @NotBlank(message = "Driver phone no can not left blank")
    private String phoneNo;

    @NotNull(message = "Driver address can not be null")
    @NotBlank(message = "Driver address can not left blank")
    private String driverAddress;

    @NotNull(message = "Driver carrier name can not be null")
    @NotBlank(message = "Driver carrier name can not left blank")
    private String carrierCompanyName;
}
