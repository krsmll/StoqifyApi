package com.knits.product.service;

import java.util.List;

import com.knits.product.entity.PurchaseOrder;
import com.knits.product.exceptions.UserException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import com.knits.product.dto.PurchaseOrderDto;
import com.knits.product.mapper.OrderLineMapper;
import com.knits.product.entity.PurchaseOrderLine;
import com.knits.product.mapper.PurchaseOrderMapper;
import com.knits.product.repository.OrderLineRepository;
import com.knits.product.repository.PurchaseOrderRepository;
import com.knits.product.repository.PurchaseOrderLineRepository;

/**
 * This is purchase order service class to handle purchase order data
 * @author Soumen Banerjee
 */
@Service("purchaseOrder")
@AllArgsConstructor
public class PurchasrOrderService {

    private final OrderLineMapper orderLineMapper;
    private final PurchaseOrderMapper purchaseOrderMapper;
    private final OrderLineRepository orderLineRepository;
    private final PurchaseOrderRepository purchaseOrderRepository;
    private final PurchaseOrderLineRepository purchaseOrderLineRepository;

    /**
     *
     * @return list of all purchases
     */
    public List<PurchaseOrderDto> getAllPurchaseOrderList() {
        return purchaseOrderMapper.toPurchaseOrderDtoList(purchaseOrderRepository.findAll());
    }

    /**
     *
     * @param purchaseOrderDto requested purchase dto to create a purchase order
     * @return list of all purchases
     */
    public List<PurchaseOrderDto> createPurchaseOrder(PurchaseOrderDto purchaseOrderDto) {
       Long lastPurchaseOrderId = purchaseOrderRepository.save(purchaseOrderMapper.toPurchaseEntity(purchaseOrderDto))
               .getId();

       Long lastOrderLineId = orderLineRepository.save(orderLineMapper.toOrderLineEntity(purchaseOrderDto.getOrderLine()))
               .getId();

       purchaseOrderLineRepository.save(new PurchaseOrderLine(0L, lastOrderLineId, lastPurchaseOrderId));
       return purchaseOrderMapper.toPurchaseOrderDtoList(purchaseOrderRepository.findAll());
    }

    /**
     *
     * @param purchaseOrderId requested purchase order id to cancel it
     * @return list of purchase order after cancle the purchase order
     */
    public List<PurchaseOrderDto> cancelPurchaseOrder(Long purchaseOrderId) {
        PurchaseOrder getPurchaseOrderLineData = purchaseOrderRepository.findById(purchaseOrderId)
                .orElseThrow(() -> new UserException("Purchase Order #" + purchaseOrderId + " not found"));

        getPurchaseOrderLineData.setType("C");
        purchaseOrderRepository.save(getPurchaseOrderLineData);
        return purchaseOrderMapper.toPurchaseOrderDtoList(purchaseOrderRepository.findAll());
    }
}
