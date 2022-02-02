package com.knits.product.controller;

import com.knits.product.dto.AdvancedShippingNoticeDto;
import com.knits.product.dto.PurchaseOrderDto;
import com.knits.product.dto.groups.UpdateGroup;
import com.knits.product.service.AdvancedShippingNoticeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Rest controller for handling HTTP requests related to advanced shipping notices.
 *
 * @author Kristjan Mill
 * @see com.knits.product.entity.AdvancedShippingNotice AdvancedShippingNotice
 * @see AdvancedShippingNoticeDto AdvancedShippingNoticeDto
 * @see AdvancedShippingNoticeService AdvancedShippingNoticeService
 */
@RestController
@AllArgsConstructor
@RequestMapping("/api/advancedshippingnotice")
public class AdvancedShippingNoticeController {
    private final AdvancedShippingNoticeService advancedShippingNoticeService;

    /**
     * Get all advanced shipping notices.
     *
     * @return Response containing all advanced shipping notices.
     * @author Kristjan Mill
     * @see AdvancedShippingNoticeDto AdvancedShippingNoticeDto
     */
    @GetMapping("/all")
    public ResponseEntity<List<AdvancedShippingNoticeDto>> getAllAdvancedShippingNotices() {
        return ResponseEntity.ok(
                advancedShippingNoticeService.fetchAllAdvancedShippingNotices()
        );
    }

    /**
     * Get advanced shipping notice by ID.
     *
     * @param id Advanced shipping notice ID specified in a query string.
     * @return Advanced shipping notice with specified ID.
     * @author Kristjan Mill
     * @see AdvancedShippingNoticeDto AdvancedShippingNoticeDto
     */
    @GetMapping("/{id}")
    public ResponseEntity<AdvancedShippingNoticeDto> getAdvancedShippingNoticeById(@PathVariable Long id) {
        return ResponseEntity.ok(
                advancedShippingNoticeService.getAdvancedShippingNoticeById(id)
        );
    }

    /**
     * Create advanced shipping notice.
     *
     * @param dto     Advanced shipping notice to create. See AdvancedShippingNoticeDto for the exact format.
     * @param request Request information autowired automatically by Spring.
     * @return Created advanced shipping notice.
     * @author Kristjan Mill
     * @see AdvancedShippingNoticeDto AdvancedShippingNoticeDto
     */
    @PostMapping
    public ResponseEntity<AdvancedShippingNoticeDto> createAdvancedShippingNotice(@RequestBody AdvancedShippingNoticeDto dto) {
        AdvancedShippingNoticeDto created = advancedShippingNoticeService.createAdvancedShippingNotice(dto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    /**
     * Update advanced shipping notice.
     *
     * @param advancedShippingNoticeDto Updated advanced shipping notice. See AdvancedShippingNoticeDto for the exact format.
     * @return Updated advanced shipping notice.
     * @author Kristjan Mill
     * @see AdvancedShippingNoticeDto AdvancedShippingNoticeDto
     */
    @PutMapping
    public ResponseEntity<AdvancedShippingNoticeDto> updateAdvancedShippingNotice(@Validated(UpdateGroup.class) @RequestBody AdvancedShippingNoticeDto advancedShippingNoticeDto) {
        return ResponseEntity.ok(
                advancedShippingNoticeService.updateAdvancedShippingNotice(advancedShippingNoticeDto)
        );
    }

    /**
     * Partially update advanced shipping notice.
     *
     * @param advancedShippingNoticeDto Partially updated advanced shipping notice. See AdvancedShippingNoticeDto for the exact format.
     * @return Updated advanced shipping notice.
     * @author Kristjan Mill
     * @see AdvancedShippingNoticeDto AdvancedShippingNoticeDto
     */
    @PatchMapping
    public ResponseEntity<AdvancedShippingNoticeDto> partialUpdateAdvancedShippingNotice(@Validated(UpdateGroup.class) @RequestBody AdvancedShippingNoticeDto advancedShippingNoticeDto) {
        return ResponseEntity.ok(
                advancedShippingNoticeService.partialUpdateAdvancedShippingNotice(advancedShippingNoticeDto)
        );
    }

    /**
     * Add packages to an advanced shipping notice.
     *
     * @param packages List of purchase orders. See PurchaseOrderDto for the exact format.
     * @param id       ID of an advanced shipping notice to add the packages to specified in a query string.
     * @return Advanced shipping notice with added packages.
     * @author Kristjan Mill
     * @see com.knits.product.entity.PurchaseOrder PurchaseOrder
     * @see PurchaseOrderDto PurchaseOrderDto
     * @see AdvancedShippingNoticeDto AdvancedShippingNoticeDto
     */
    @PostMapping("/{id}/packages")
    public ResponseEntity<AdvancedShippingNoticeDto> addPackagesToAdvancedShippingNotice(@RequestBody List<PurchaseOrderDto> packages, @PathVariable Long id) {
        return ResponseEntity.ok(
                advancedShippingNoticeService.addPackagesToAdvancedShippingNotice(packages, id)
        );
    }

    /**
     * Remove package from an advanced shipping order.
     *
     * @param id        ID of an advanced shipping notice to remove the package from.
     * @param packageId ID of a purchase order that needs to be removed.
     * @return Advanced shipping order with the package removed.
     * @author Kristjan Mill
     * @see com.knits.product.entity.PurchaseOrder PurchaseOrder
     * @see PurchaseOrderDto PurchaseOrderDto
     * @see AdvancedShippingNoticeDto AdvancedShippingNoticeDto
     */
    @DeleteMapping("/{id}/packages/{packageId}")
    public ResponseEntity<AdvancedShippingNoticeDto> removePackageFromAdvancedShippingNoticeById(@PathVariable Long id, @PathVariable Long packageId) {
        return ResponseEntity.ok(
                advancedShippingNoticeService.removePackageFromAdvancedShippingNoticeById(packageId, id)
        );
    }
}
