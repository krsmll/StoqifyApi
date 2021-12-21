package com.knits.product.service;

import com.knits.product.dto.GroupDto;
import com.knits.product.dto.ItemDto;
import com.knits.product.entity.Item;
import com.knits.product.entity.User;
import com.knits.product.exceptions.ExceptionCodes;
import com.knits.product.exceptions.UserException;
import com.knits.product.mapper.GroupMapper;
import com.knits.product.mapper.ItemMapper;
import com.knits.product.repository.GroupRepository;
import com.knits.product.repository.ItemRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service("item")
@Transactional
@AllArgsConstructor
public class ItemService{

    private final ItemMapper itemMapper;
    private final ItemRepository itemRepository;

    /**
     *
     * @return list of all items
     */
    public List<ItemDto> getAllItems() {
        log.debug("Request to get all Groups");

        return itemRepository.findAll().stream().map(itemMapper::toDto).collect(Collectors.toList());
    }

    /**
     * Save a employee.
     *
     * @param itemDto the entity to save.
     * @return the persisted entity.
     */
    public ItemDto createItem(ItemDto itemDto){
        log.debug("Request to save User : {}", itemDto);
        Item item = itemMapper.toEntity(itemDto);
        item = itemRepository.save(item);
        return itemMapper.toDto(item);
    }

}
