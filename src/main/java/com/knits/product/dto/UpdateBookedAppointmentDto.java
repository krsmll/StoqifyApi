package com.knits.product.dto;

import lombok.Data;
import javax.validation.constraints.Pattern;

@Data
public class UpdateBookedAppointmentDto {

    private Long id;
    private String status;

    @Pattern(regexp = "^[A-Za-z]*$", message = "Updated booking comment must be only characters")
    private String comment;
}
