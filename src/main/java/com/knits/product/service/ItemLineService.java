package com.knits.product.service;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import lombok.AllArgsConstructor;
import java.util.stream.Collectors;
import com.knits.product.entity.ItemLine;
import com.knits.product.dto.ItemLineDto;
import com.knits.product.entity.ItemLinkAsn;
import com.knits.product.mapper.ItemLineMapper;
import org.springframework.stereotype.Service;
import com.knits.product.entity.ItemLinkItemLine;
import com.knits.product.exceptions.UserException;
import com.knits.product.repository.ItemLineRepository;
import com.knits.product.repository.ItemLinkAsnRepository;
import com.knits.product.repository.ItemLinkItemLineRepository;

/**
 * This is a service class to handle item line data
 * @author Soumen Banerjee
 */
@AllArgsConstructor
@Service("itemline")
public class ItemLineService {

    private final ItemLineMapper itemLineMapper;
    private final ItemLineRepository itemLineRepository;
    private final ItemLinkAsnRepository itemLinkAsnRepository;
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
     * @param itemLineDto requested item line data to create a new one
     * @return updated list of item line including new one
     */
    public List<ItemLineDto> createNewItemLine(ItemLineDto itemLineDto) {

       List<ItemLinkItemLine> itemLineLinkList = new ArrayList<>();
       List<Long> itemIds = itemLinkItemLineRepository.findAll().stream()
                .map(ItemLinkItemLine::getItemId).collect(Collectors.toList());

       ItemLine newItemLine = new ItemLine(new Date(), 0L, itemLineDto.getComment());
       itemLineRepository.save(newItemLine);

        Long getlastItemLineId = newItemLine.getId();

        itemLineDto.getItems().forEach(getItemLineData -> {
            if(!itemIds.contains(getItemLineData.getId())) {
                itemLineLinkList.add(new ItemLinkItemLine(0L, getlastItemLineId, getItemLineData.getId()));
            }
        });

        newItemLine.setItemCount(itemLineLinkList.stream().count());

        itemLineRepository.save(newItemLine);
        itemLinkItemLineRepository.saveAll(itemLineLinkList);
        itemLinkAsnRepository.save(new ItemLinkAsn(0L, getlastItemLineId, itemLineDto.getAsn().getId()));

        return getAllItemList();
    }

    /**
     *
     * @param itemLineDto requested updated item line data to updated existing record
     * @return full list of item line
     */
    public List<ItemLineDto> updateItemLine(ItemLineDto itemLineDto) {
        List<ItemLinkItemLine> itemLineLinkList = new ArrayList<>();

        List<ItemLinkItemLine> getItemLineLinkList = itemLinkItemLineRepository.findByItemLineId(itemLineDto.getItemLineid());
        ItemLinkAsn getitemLinkASN = itemLinkAsnRepository.findByItemLineId(itemLineDto.getItemLineid())
                .orElseThrow(() -> new UserException("ItemLinkAsn #" + itemLineDto.getItemLineid() + " not found by requested item line id"));

        itemLinkItemLineRepository.deleteAll(getItemLineLinkList);

        itemLineDto.getItems().forEach(getRequestedItem -> {
            itemLineLinkList.add(new ItemLinkItemLine(0L, itemLineDto.getItemLineid(), getRequestedItem.getId()));
        });
        getitemLinkASN.setAdvancedShippingNoticeId(itemLineDto.getAsn().getId());

        itemLinkAsnRepository.save(getitemLinkASN);
        itemLinkItemLineRepository.saveAll(itemLineLinkList);

        return getAllItemList();
    }
}
