package com.knits.product.service;

import com.knits.product.dto.ItemDto;
import com.knits.product.entity.Item;
import com.knits.product.exceptions.UserException;
import com.knits.product.mapper.ItemMapper;
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
public class ItemService {

    private final ItemMapper itemMapper;
    private final ItemRepository itemRepository;

    /**
     * @return list of all items
     */
    public List<ItemDto> getAllItems() {
        log.debug("Request to get all Item");

        return itemRepository.findAll().stream().map(itemMapper::toDto).collect(Collectors.toList());
    }

    /**
     * Save a employee.
     *
     * @param itemDto the entity to save.
     * @return the persisted entity.
     */
    public ItemDto createItem(ItemDto itemDto) {
        log.debug("Request to save Item : {}", itemDto);
        Item item = itemMapper.toEntity(itemDto);
        System.out.println(item);
        return itemMapper.toDto(itemRepository.save(item));
    }

    /**
     * @return item by name
     */
    public ItemDto getItemByName(String name) {
        log.debug("Request to get  Item by name : {}", name);
        return itemMapper.toDto(itemRepository.findByName(name));
    }

    /**
     * Save a employee.
     *
     * @param id the entity to save.
     */
    public void deleteItemById(Long id) {
        log.debug("Delete item by name : {}", id);
        itemRepository.deleteById(id);
    }

    /**
     * Save a employee.
     *
     * @param itemDto the entity to save.
     * @return the persisted entity.
     */
    public ItemDto partialUpdateItemData(ItemDto itemDto) {
        log.debug("Request to partially update Item : {}", itemDto);
        Item item = itemRepository.findById(itemDto.getId())
                .orElseThrow(() -> new UserException("Role#" + itemDto.getId() + " not found"));
        itemMapper.partialUpdate(item, itemDto);
        itemRepository.save(item);
        return itemMapper.toDto(item);
    }

    /**
     * Save a employee.
     *
     * @param itemDto the entity to save.
     * @return the persisted entity.
     */
    public ItemDto updateItemData(ItemDto itemDto) {
        log.debug("Request to update Item : {}", itemDto);
        Item item = itemRepository.findById(itemDto.getId())
                .orElseThrow(() -> new UserException("Role#" + itemDto.getId() + " not found"));
        itemMapper.update(item, itemDto);
        itemRepository.save(item);
        return itemMapper.toDto(item);
    }
}
