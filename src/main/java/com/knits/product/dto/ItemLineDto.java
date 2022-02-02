package com.knits.product.dto;

import lombok.Data;
import java.util.Date;
import java.util.List;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import com.knits.product.dto.groups.InsertGroup;
import com.knits.product.dto.groups.UpdateGroup;
import com.knits.product.entity.AdvancedShippingNotice;


@Data
public class ItemLineDto {

    @NotNull(groups = UpdateGroup.class)
    private Long itemLineid;

    private Long itemCount;

    @NotNull(message = "Comment can not be null", groups = {InsertGroup.class, UpdateGroup.class})
    @Pattern(regexp = "^[A-Za-z]*$", message = "Item comment should contain only characters")
    private String comment;
    private Date enteredDate;

    @NotNull(message = "Items can not null", groups = {InsertGroup.class, UpdateGroup.class})
    private List<ItemDto> items;

    @NotNull(message = "ASN can not be null", groups = {InsertGroup.class, UpdateGroup.class})
    private AdvancedShippingNotice asn;
}
