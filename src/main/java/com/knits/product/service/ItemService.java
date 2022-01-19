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
     * Get list of all items.
     *
     * @return list of all items
     */
    public List<ItemDto> getAllItems() {
        log.debug("Request to get all Item");

        return itemRepository.findAll().stream().map(itemMapper::toDto).collect(Collectors.toList());
    }

    /**
     * Create an item.
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
     * Get item by name.
     *
     * @param name Item name.
     * @return item by name
     */
    public ItemDto getItemByName(String name) {
        log.debug("Request to get  Item by name : {}", name);
        return itemMapper.toDto(itemRepository.findByName(name));
    }

    /**
     * Delete item by ID.
     *
     * @param id the entity to save.
     */
    public void deleteItemById(Long id) {
        log.debug("Delete item by name : {}", id);
        itemRepository.deleteById(id);
    }

    /**
     * Partially update item data.
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
     * Update item data.
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


    /**
     * Edit status of an item.
     *
     * @param itemId ID of an item.
     * @param newStatus New status for an item.
     * @return Item with newly assigned status for it.
     */
    public ItemDto editItemStatus(Long itemId, String newStatus) {
        log.debug("Request to edit item status with an ID of : {}", itemId);
        Item item = itemRepository.findById(itemId).orElseThrow(() ->
                new UserException(String.format("Role with an ID of %d not found.", itemId))
        );
        item.setStatus(newStatus);
        return itemMapper.toDto(itemRepository.save(item));
    }

}
