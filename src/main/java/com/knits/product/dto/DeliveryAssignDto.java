package com.knits.product.dto;

import lombok.Data;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

@Data
public class DeliveryAssignDto {

    private Long id;
    private String status;
    private UserDto userDto;
    private DockDto dockDto;

    @JsonFormat(pattern = "dd.mm.YYYY hh:mm")
    private Date arrivalTime;
    private AdvancedShippingNoticeDto asn;
}
