package com.knits.product.mapper;

import java.util.List;
import com.knits.product.dto.PurchaseOrderDto;
import org.mapstruct.Mapper;
import com.knits.product.entity.PurchaseOrder;

/**
 * This is Purchase Order mapper interface purchase entity to dto and vise versa
 * @author Soumen Banerjee
 */
@Mapper(componentModel = "spring")
public interface PurchaseOrderMapper {

    List<PurchaseOrderDto> toPurchaseOrderDtoList(List<PurchaseOrder> purchaseOrders);
    PurchaseOrderDto toDto(PurchaseOrder purchaseOrder);
    PurchaseOrder toPurchaseEntity(PurchaseOrderDto purchaseOrderDto);
}
