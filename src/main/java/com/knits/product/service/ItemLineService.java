package com.knits.product.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.knits.product.entity.Item;
import com.knits.product.entity.ItemLinkItemLine;
import com.knits.product.mapper.ItemMapper;
import lombok.AllArgsConstructor;
import java.util.stream.Collectors;
import com.knits.product.entity.ItemLine;
import com.knits.product.dto.ItemLineDto;
import com.knits.product.mapper.ItemLineMapper;
import org.springframework.stereotype.Service;
import com.knits.product.repository.ItemLineRepository;
import com.knits.product.repository.ItemLinkItemLineRepository;

/**
 * This is a service class to handle item line data
 * @author Soumen Banerjee
 */
@AllArgsConstructor
@Service("itemline")
public class ItemLineService {

    private final ItemMapper itemMapper;
    private final ItemLineMapper itemLineMapper;
    private final ItemLineRepository itemLineRepository;
    private final ItemLinkItemLineRepository itemLinkItemLineRepository;

    /**
     *
     * @return item line list
     */
    public List<ItemLineDto> getAllItemList() {
        return itemLineRepository.findAll().stream()
                .map(itemLineMapper::toDto).collect(Collectors.toList());
    }

    /**
     *
     * @return item lines
     */
    public List<ItemLineDto> createNewItemLine(ItemLineDto itemLineDto) {

        List<ItemLinkItemLine> itemLineLinkList = new ArrayList<>();

        List<Long> itemIds = itemLinkItemLineRepository.findAll().stream()
                .map(ItemLinkItemLine::getItemId).collect(Collectors.toList());
        itemLineDto.getItems().forEach(getItemLineData -> {
            if(!itemIds.contains(getItemLineData.getId())) {
                itemLineLinkList.add(new ItemLinkItemLine(0L, itemLineDto.getItemLineid(), getItemLineData.getId()));
            }
        });

        itemLinkItemLineRepository.saveAll(itemLineLinkList);
        //itemLineRepository.save(new ItemLine(new Date(), 1L, itemLineDto.getComment()));
        /*
        itemLinkItemLineRepository.findAll().forEach(k -> {
            System.out.println("ITEM ID --> " + k.getItemId() + " ITEM LINE ID --> " + k.getItemLineId());
        });*/
        return getAllItemList();
    }
}
