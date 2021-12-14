package com.knits.product.service;

import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import com.knits.product.dto.PurchaseOrderDto;
import com.knits.product.mapper.PurchaseOrderMapper;
import com.knits.product.repository.PurchaseOrderRepository;

/**
 * This service class is responsible for handling purchase order data
 * @author Soumen Banerjee
 */

@Service("purchaseOrder")
@AllArgsConstructor
public class PurchaseOrderService {

    private final PurchaseOrderMapper purchaseOrderMapper;
    private final PurchaseOrderRepository purchaseOrderRepository;

    public List<PurchaseOrderDto> getAllPurchaseDtoList() {
        return purchaseOrderMapper.toPurchaseDtoList(purchaseOrderRepository.findAll());
    }
}
