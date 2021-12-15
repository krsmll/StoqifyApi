package com.knits.product.mapper;

import java.util.List;
import org.mapstruct.Mapper;
import com.knits.product.dto.OrderLineDto;
import com.knits.product.entity.OrderLine;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface OrderLineMapper {

    List<OrderLineDto> toPurchaseDtoList(List<OrderLine> purchaseOrderEntityList);
    void editOrderLine(@MappingTarget OrderLine orderLine, OrderLineDto purchaseOrder);
}
