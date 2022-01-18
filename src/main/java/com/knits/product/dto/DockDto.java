package com.knits.product.dto;

import lombok.Data;
import java.util.Date;

@Data
public class DockDto {

    private Long id;
    private Long supervisorId;
    private Long dockDimentaion;
    private String status;
    private String comment;
    private Date created;
    private Integer itemCount;
    private Date reservationDate;
    private Date preRegistration;
    private Date lastUpdated;
}
