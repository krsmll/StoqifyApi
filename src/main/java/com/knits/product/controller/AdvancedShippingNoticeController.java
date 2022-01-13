package com.knits.product.controller;

import com.knits.product.dto.AdvancedShippingNoticeDto;
import com.knits.product.dto.PurchaseOrderDto;
import com.knits.product.dto.groups.InsertGroup;
import com.knits.product.dto.groups.UpdateGroup;
import com.knits.product.exceptions.SystemException;
import com.knits.product.service.AdvancedShippingNoticeService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/advancedshippingnotice")
public class AdvancedShippingNoticeController {
    private final AdvancedShippingNoticeService advancedShippingNoticeService;

    @GetMapping("/all")
    public ResponseEntity<List<AdvancedShippingNoticeDto>> getAllAdvancedShippingNotices() {
        return ResponseEntity.ok(
                advancedShippingNoticeService.fetchAllAdvancedShippingNotices()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdvancedShippingNoticeDto> getAdvancedShippingNoticeById(@PathVariable Long id) {
        return ResponseEntity.ok(
                advancedShippingNoticeService.getAdvancedShippingNoticeById(id)
        );
    }

    @PostMapping
    public ResponseEntity<AdvancedShippingNoticeDto> createAdvancedShippingNotice(@Validated(InsertGroup.class) AdvancedShippingNoticeDto dto, HttpServletRequest request) {
        AdvancedShippingNoticeDto created = advancedShippingNoticeService.createAdvancedShippingNotice(dto);
        URI uri;
        try {
            uri = new URI(request.getRequestURL().append("/").append(created.getId()).toString());
        } catch (URISyntaxException e) {
            throw new SystemException(e);
        }

        return ResponseEntity.created(uri).body(created);
    }

    @PutMapping
    public ResponseEntity<AdvancedShippingNoticeDto> updateAdvancedShippingNotice(@Validated(UpdateGroup.class) AdvancedShippingNoticeDto advancedShippingNoticeDto) {
        return ResponseEntity.ok(
                advancedShippingNoticeService.updateAdvancedShippingNotice(advancedShippingNoticeDto)
        );
    }

    @PatchMapping
    public ResponseEntity<AdvancedShippingNoticeDto> partialUpdateAdvancedShippingNotice(@Validated(UpdateGroup.class) AdvancedShippingNoticeDto advancedShippingNoticeDto) {
        return ResponseEntity.ok(
                advancedShippingNoticeService.partialUpdateAdvancedShippingNotice(advancedShippingNoticeDto)
        );
    }

    @PostMapping("/{id}/packages")
    public ResponseEntity<AdvancedShippingNoticeDto> addPackagesToAdvancedShippingNotice(List<PurchaseOrderDto> packages, @PathVariable Long id) {
        return ResponseEntity.ok(
                advancedShippingNoticeService.addPackagesToAdvancedShippingNotice(packages, id)
        );
    }

    @DeleteMapping("/{id}/packages/{packageId}")
    public ResponseEntity<AdvancedShippingNoticeDto> removePackageFromAdvancedShippingNoticeById(@PathVariable Long id, @PathVariable Long packageId) {
        return ResponseEntity.ok(
                advancedShippingNoticeService.removePackageFromAdvancedShippingNoticeById(packageId, id)
        );
    }
}
