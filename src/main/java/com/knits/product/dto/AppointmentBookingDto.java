package com.knits.product.dto;

import lombok.Data;
import java.util.Date;
import com.knits.product.entity.AdvancedShippingNotice;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class AppointmentBookingDto {

    private Long id;

    @NotNull(message = "Operator ID can not be null")
    @NotBlank(message = "Operator ID can not be blank")
    private Long operatorId;

    @NotNull(message = "Carrier ID can not be null")
    @NotBlank(message = "Carrier ID can not be null")
    private Long carrierId;

    @NotNull(message = "Dock ID can not be null")
    @NotBlank(message = "Dockk ID can not be null")
    private Long dockId;

    @NotNull(message = "Status can not be null")
    @NotBlank(message = "Status can not be blank")
    @Pattern(regexp = "^[A-Za-z]*$", message = "Appointment status should contain only characters")
    private String status;

    @NotNull(message = "Comment can not be null")
    @NotNull(message = "Comment can not be blank")
    @Pattern(regexp = "^[A-Za-z]*$", message = "Appointment comment should contain only characters")
    private String comment;

    private Date createdData;
    private Date lastUpdated;
    private AdvancedShippingNotice advancedShippingNotice;
}
