package com.knits.product.controller;


import com.knits.product.dto.ItemDto;
import com.knits.product.service.ItemService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class ItemController {

private final ItemService itemService;

    @PostMapping(value = "/item")
    public ResponseEntity<ItemDto> createItem(@Valid @RequestBody ItemDto itemDto) {
        log.debug("REST request to createUser User ");
        return ResponseEntity.ok().body(itemService.createItem(itemDto));
    }

}
