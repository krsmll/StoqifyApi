package com.knits.product.controller;


import com.knits.product.dto.ItemDto;
import com.knits.product.service.ItemService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class ItemController {

    private final ItemService itemService;

    @PostMapping(value = "/item")
    public ResponseEntity<ItemDto> createItem(@RequestBody ItemDto itemDto) {
        log.debug("REST request to createUser User ");
        return ResponseEntity.ok().body(itemService.createItem(itemDto));
    }

    @GetMapping("/item/all")
    public ResponseEntity<List<ItemDto>> getAllItems() {
        log.debug("REST request to get all items");
        return ResponseEntity.ok().body(itemService.getAllItems());
    }

    @GetMapping("/search/{name}")
    public ResponseEntity<ItemDto> getItemByName(@PathVariable String name) {
        log.debug("REST request to find item by name" + name);
        return ResponseEntity.ok().body(itemService.getItemByName(name));
    }

    @DeleteMapping("/item/{id}")
    public ResponseEntity<ItemDto> deleteItemById(@PathVariable Long id) {
        log.debug("REST request to delete Item : {}", id);
        itemService.deleteItemById(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping(value = "/item")
    public ResponseEntity<ItemDto> partialUpdateRole(@RequestBody ItemDto itemDto) {
        log.debug("REST request to update Item " + itemDto);
        return ResponseEntity.ok().body(itemService.partialUpdateItemData(itemDto));
    }


}
