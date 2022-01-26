package com.knits.product.dto;

import lombok.Data;
import java.util.Date;
import java.util.List;
import com.knits.product.entity.Item;
import com.knits.product.entity.AdvancedShippingNotice;

@Data
public class ItemLineDto {

    private Long id;
    private Long itemCount;
    private String comment;
    private Date enteredDate;
    private List<Item> items;
    private AdvancedShippingNotice asn;
}
