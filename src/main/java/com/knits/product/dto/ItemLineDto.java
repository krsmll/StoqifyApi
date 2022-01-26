package com.knits.product.dto;

import lombok.Data;
import java.util.Date;
import java.util.List;
import javax.validation.constraints.NotNull;
import com.knits.product.entity.AdvancedShippingNotice;


@Data
public class ItemLineDto {

    @NotNull(message = "Item Line ID can not be null")
    private Long itemLineid;

    private Long itemCount;
    private String comment;
    private Date enteredDate;

    @NotNull(message = "Items can not null")
    private List<ItemDto> items;

    @NotNull(message = "ASN can not be null")
    private AdvancedShippingNotice asn;
}
