package com.knits.product.service;

import java.util.List;
import lombok.AllArgsConstructor;
import java.util.stream.Collectors;
import com.knits.product.dto.ItemLineDto;
import com.knits.product.mapper.ItemLineMapper;
import org.springframework.stereotype.Service;
import com.knits.product.repository.ItemLineRepository;

/**
 * This is a service class to handle item line data
 * @author Soumen Banerjee
 */
@AllArgsConstructor
@Service("itemline")
public class ItemLineService {

    private final ItemLineMapper itemLineMapper;
    private final ItemLineRepository itemLineRepository;

    /**
     *
     * @return item line list
     */
    public List<ItemLineDto> getAllItemList() {
        return itemLineRepository.findAll().stream().map(itemLineMapper::toDto).collect(Collectors.toList());
    }

    /**
     *
     * @return item lines
     */
    public List<ItemLineDto> createNewItemLine(ItemLineDto itemLineDto) {
        itemLineRepository.save(itemLineMapper.toEntity(itemLineDto));
        return getAllItemList();
    }
}
