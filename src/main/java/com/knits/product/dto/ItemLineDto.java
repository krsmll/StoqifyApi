package com.knits.product.dto;

import lombok.Data;
import java.util.Date;
import java.util.List;
import javax.validation.constraints.NotNull;
import com.knits.product.entity.AdvancedShippingNotice;


@Data
public class ItemLineDto {

    private Long itemLineid;

    private Long itemCount;

    @NotNull(message = "Comment can not be null")
    private String comment;
    private Date enteredDate;

    @NotNull(message = "Items can not null")
    private List<ItemDto> items;

    @NotNull(message = "ASN can not be null")
    private AdvancedShippingNotice asn;
}
