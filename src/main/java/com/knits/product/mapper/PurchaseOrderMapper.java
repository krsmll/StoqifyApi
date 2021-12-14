package com.knits.product.mapper;

import java.util.List;
import org.mapstruct.Mapper;
import com.knits.product.dto.PurchaseOrderDto;
import com.knits.product.entity.PurchaseOrderEntity;

@Mapper(componentModel = "spring")
public interface PurchaseOrderMapper {

    List<PurchaseOrderDto> toPurchaseDtoList(List<PurchaseOrderEntity> purchaseOrderEntityList);
}
