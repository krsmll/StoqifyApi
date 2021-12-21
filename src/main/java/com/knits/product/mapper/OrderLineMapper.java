package com.knits.product.mapper;

import java.util.List;
import org.mapstruct.Mapper;
import com.knits.product.dto.OrderLineDto;
import com.knits.product.entity.OrderLine;
import org.mapstruct.MappingTarget;

/**
 * This is order line mapper interface to map order line dto to entity and vise versa
 * @author Soumen Banerjee
 */
@Mapper(componentModel = "spring")
public interface OrderLineMapper {

    List<OrderLineDto> toPurchaseDtoList(List<OrderLine> purchaseOrderEntityList);
    void editOrderLine(@MappingTarget OrderLine orderLine, OrderLineDto purchaseOrder);
    OrderLine toOrderLineEntity(OrderLineDto orderLineDto);
}
