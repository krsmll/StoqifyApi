package com.knits.product.dto;

import lombok.Data;
import java.util.Date;
import com.knits.product.entity.AdvancedShippingNotice;

@Data
public class AppointmentBookingDto {

    private Long id;
    private Long operatorId;
    private Long carrierId;
    private Long dockId;
    private String status;
    private String comment;
    private Date createdData;
    private Date lastUpdated;
    private AdvancedShippingNotice advancedShippingNotice;
}
