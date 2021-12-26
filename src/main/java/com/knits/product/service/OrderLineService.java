package com.knits.product.service;

import java.util.List;
import lombok.AllArgsConstructor;
import java.util.stream.Collectors;
import com.knits.product.entity.OrderLine;
import com.knits.product.dto.OrderLineDto;
import org.springframework.stereotype.Service;
import com.knits.product.mapper.OrderLineMapper;
import com.knits.product.exceptions.UserException;
import com.knits.product.repository.OrderLineRepository;

/**
 * This service class is responsible for handling purchase order data
 * @author Soumen Banerjee
 */
@Service("orderLine")
@AllArgsConstructor
public class OrderLineService {

    private final OrderLineMapper orderLineMapper;
    private final OrderLineRepository orderLineRepository;

    /**
     *
     * @return list of order lines
     */
    public List<OrderLineDto> getAllOrderLines() {
        return getAllActiveOrderLineList();
    }

    /**
     *
     * @param orderLineDto requested order line to edit
     * @return order line dto list with edited order line
     */
    public List<OrderLineDto> editOrderLine(OrderLineDto orderLineDto) {
        OrderLine getSingleOrderLineData = orderLineRepository
                .findById(orderLineDto.getId())
                .orElseThrow(() -> new UserException("Order Line#" + orderLineDto.getId() + " not found"));

        if(!getSingleOrderLineData.getActive())
            throw new UserException("Order Line#" + orderLineDto.getId() + " not found");

        orderLineMapper.editOrderLine(getSingleOrderLineData, orderLineDto);
        orderLineRepository.save(getSingleOrderLineData);

        return getAllActiveOrderLineList();
    }

    /**
     *
     * @param orderLineId requested order line id to deactivate
     * @return list of only activated order line
     */
    public List<OrderLineDto> deactivateOrderLine(Long orderLineId) {
        OrderLine getSingleOrderLineData = orderLineRepository
                .findById(orderLineId)
                .orElseThrow(() -> new UserException("Order Line#" + orderLineId + " not found"));

        getSingleOrderLineData.setActive(false);
        orderLineRepository.save(getSingleOrderLineData);

        return getAllActiveOrderLineList();
    }

    /**
     *
     * @return all active order line data
     */
    private List<OrderLineDto> getAllActiveOrderLineList() {
        return orderLineMapper.toPurchaseDtoList(orderLineRepository.findAll()
                .stream().filter(getOrderLine -> getOrderLine.getActive().equals(Boolean.TRUE))
                .collect(Collectors.toList()));
    }
}
