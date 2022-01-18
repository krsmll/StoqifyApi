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

/**
 * Rest controller for handling HTTP requests related to advanced shipping notices.
 *
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
     * @see AdvancedShippingNoticeDto AdvancedShippingNoticeDto
     * @return Response containing all advanced shipping notices.
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
     * @see AdvancedShippingNoticeDto AdvancedShippingNoticeDto
     * @param id Advanced shipping notice ID specified in a query string.
     * @return Advanced shipping notice with specified ID.
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
     * @see AdvancedShippingNoticeDto AdvancedShippingNoticeDto
     * @param dto Advanced shipping notice to create. See AdvancedShippingNoticeDto for the exact format.
     * @param request Request information autowired automatically by Spring.
     * @return Created advanced shipping notice.
     */
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

    /**
     * Update advanced shipping notice.
     *
     * @see AdvancedShippingNoticeDto AdvancedShippingNoticeDto
     * @param advancedShippingNoticeDto Updated advanced shipping notice. See AdvancedShippingNoticeDto for the exact format.
     * @return Updated advanced shipping notice.
     */
    @PutMapping
    public ResponseEntity<AdvancedShippingNoticeDto> updateAdvancedShippingNotice(@Validated(UpdateGroup.class) AdvancedShippingNoticeDto advancedShippingNoticeDto) {
        return ResponseEntity.ok(
                advancedShippingNoticeService.updateAdvancedShippingNotice(advancedShippingNoticeDto)
        );
    }

    /**
     * Partially update advanced shipping notice.
     *
     * @see AdvancedShippingNoticeDto AdvancedShippingNoticeDto
     * @param advancedShippingNoticeDto Partially updated advanced shipping notice. See AdvancedShippingNoticeDto for the exact format.
     * @return Updated advanced shipping notice.
     */
    @PatchMapping
    public ResponseEntity<AdvancedShippingNoticeDto> partialUpdateAdvancedShippingNotice(@Validated(UpdateGroup.class) AdvancedShippingNoticeDto advancedShippingNoticeDto) {
        return ResponseEntity.ok(
                advancedShippingNoticeService.partialUpdateAdvancedShippingNotice(advancedShippingNoticeDto)
        );
    }

    /**
     * Add packages to an advanced shipping notice.
     *
     * @see com.knits.product.entity.PurchaseOrder PurchaseOrder
     * @see PurchaseOrderDto PurchaseOrderDto
     * @see AdvancedShippingNoticeDto AdvancedShippingNoticeDto
     * @param packages List of purchase orders. See PurchaseOrderDto for the exact format.
     * @param id ID of an advanced shipping notice to add the packages to specified in a query string.
     * @return Advanced shipping notice with added packages.
     */
    @PostMapping("/{id}/packages")
    public ResponseEntity<AdvancedShippingNoticeDto> addPackagesToAdvancedShippingNotice(List<PurchaseOrderDto> packages, @PathVariable Long id) {
        return ResponseEntity.ok(
                advancedShippingNoticeService.addPackagesToAdvancedShippingNotice(packages, id)
        );
    }

    /**
     * Remove package from an advanced shipping order.
     *
     * @see com.knits.product.entity.PurchaseOrder PurchaseOrder
     * @see PurchaseOrderDto PurchaseOrderDto
     * @see AdvancedShippingNoticeDto AdvancedShippingNoticeDto
     * @param id ID of an advanced shipping notice to remove the package from.
     * @param packageId ID of a purchase order that needs to be removed.
     * @return Advanced shipping order with the package removed.
     */
    @DeleteMapping("/{id}/packages/{packageId}")
    public ResponseEntity<AdvancedShippingNoticeDto> removePackageFromAdvancedShippingNoticeById(@PathVariable Long id, @PathVariable Long packageId) {
        return ResponseEntity.ok(
                advancedShippingNoticeService.removePackageFromAdvancedShippingNoticeById(packageId, id)
        );
    }
}
