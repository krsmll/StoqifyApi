package com.knits.product.dto;

import com.knits.product.dto.groups.InsertGroup;
import com.knits.product.dto.groups.UpdateGroup;
import lombok.Data;
import java.util.Date;
import java.util.List;
import javax.validation.constraints.NotNull;
import com.knits.product.entity.AdvancedShippingNotice;


@Data
public class ItemLineDto {

    @NotNull(groups = UpdateGroup.class)
    private Long itemLineid;

    private Long itemCount;

    @NotNull(message = "Comment can not be null", groups = {InsertGroup.class, UpdateGroup.class})
    private String comment;
    private Date enteredDate;

    @NotNull(message = "Items can not null", groups = {InsertGroup.class, UpdateGroup.class})
    private List<ItemDto> items;

    @NotNull(message = "ASN can not be null", groups = {InsertGroup.class, UpdateGroup.class})
    private AdvancedShippingNotice asn;
}
