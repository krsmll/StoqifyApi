package com.knits.product.controller;

import java.util.List;

import com.knits.product.dto.groups.InsertGroup;
import com.knits.product.dto.groups.UpdateGroup;
import lombok.AllArgsConstructor;
import com.knits.product.dto.ItemLineDto;
import com.knits.product.service.ItemLineService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * This is a REST API class to handle Item Line data
 * @author Soumen Banerjee
 */
@RestController
@AllArgsConstructor
@RequestMapping("api/itemline")
public class ItemLineController {

    private final ItemLineService itemLineService;

    @GetMapping
    public ResponseEntity<List<ItemLineDto>> getAllItemLineData() {
        return ResponseEntity.ok().body(itemLineService.getAllItemList());
    }

    @PostMapping
    public ResponseEntity<List<ItemLineDto>> createItemLineData(@Validated(InsertGroup.class) @RequestBody ItemLineDto itemLineDto) {
        return ResponseEntity.ok().body(itemLineService.createNewItemLine(itemLineDto));
    }

    @PutMapping
    public ResponseEntity<List<ItemLineDto>> editItemLineData(@Validated(UpdateGroup.class) @RequestBody ItemLineDto itemLineDto) {
        return ResponseEntity.ok().body(itemLineService.updateItemLine(itemLineDto));
    }
}
