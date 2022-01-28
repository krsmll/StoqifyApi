package com.knits.product.dto;

import lombok.Data;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class UpdateBookedAppointment {

    @NotNull(message = "ID can not be null")
    @NotBlank(message = "ID can not be blank")
    private Long id;

    @NotNull(message = "Status can not be null")
    @NotBlank(message = "Status can not be blank")
    @Pattern(regexp = "^[A-Z]*$", message = "Appointment status should contain only character")
    private String status;
    private String comment;
}
