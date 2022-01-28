package com.knits.product.dto;

import lombok.Data;

import javax.validation.constraints.Pattern;
import java.util.Date;

@Data
public class DockDto {

    private Long id;

    @Pattern(regexp = "^[0-9]*$", message = "Supervisor ID should contain only number")
    private Long supervisorId;

    @Pattern(regexp = "^[0-9]*$", message = "Dock dimention should contain only number")
    private Long dockDimentaion;

    @Pattern(regexp = "^[A-Za-z]*$", message = "Status should contain only characters")
    private String status;

    @Pattern(regexp = "^[A-Za-z]*$", message = "Comment should contain only characters")
    private String comment;
    private Date created;

    @Pattern(regexp = "^[0-9]*$", message = "Item count should contain only number")
    private Integer itemCount;
    private Date reservationDate;
    private Date preRegistration;
    private Date lastUpdated;
}
