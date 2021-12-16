package com.knits.product.service;

import java.util.List;
import com.knits.product.entity.OrderLine;
import com.knits.product.exceptions.UserException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import com.knits.product.dto.OrderLineDto;
import com.knits.product.mapper.OrderLineMapper;
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
        return orderLineMapper.toPurchaseDtoList(orderLineRepository.findAll());
    }

    /**
     *
     * @param orderLineDto requested order line to edit
     * @return order line dto list with edited order line
     */
    public List<OrderLineDto> editOrderLine(OrderLineDto orderLineDto) {
        OrderLine getSingleOrderLineData = orderLineRepository
                .findById(orderLineDto.getId()).orElseThrow(() -> new UserException("Order Line#" + orderLineDto.getId() + " not found"));

        orderLineMapper.editOrderLine(getSingleOrderLineData, orderLineDto);
        orderLineRepository.save(getSingleOrderLineData);

        return orderLineMapper.toPurchaseDtoList(orderLineRepository.findAll());
    }
}
