package com.knits.product.dto;

import lombok.Data;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotBlank;

@Data
public class AppointmentBookingAssignASNDto {

    @NotNull(message = "Appointment ID can not be null")
    private Long appointmentId;

    @NotNull(message = "ASN ID can not be null")
    private Long asnId;
}
