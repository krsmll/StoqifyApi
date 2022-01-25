package com.knits.product.dto;

import lombok.Data;

@Data
public class UpdateBookedAppointmentDto {

    private Long id;
    private String status;
    private String comment;
}
