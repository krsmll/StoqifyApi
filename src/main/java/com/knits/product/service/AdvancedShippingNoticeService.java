package com.knits.product.service;

import com.knits.product.dto.AdvancedShippingNoticeDto;
import com.knits.product.dto.PurchaseOrderDto;
import com.knits.product.entity.AdvancedShippingNotice;
import com.knits.product.exceptions.UserException;
import com.knits.product.mapper.AdvancedShippingNoticeMapper;
import com.knits.product.mapper.PurchaseOrderMapper;
import com.knits.product.repository.AdvancedShippingNoticeRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Business logic for AdvancedShippingNotice.
 *
 * @see AdvancedShippingNotice AdvancedShippingNotice
 */
@Slf4j
@Service
@Transactional
@AllArgsConstructor
public class AdvancedShippingNoticeService {
    private final AdvancedShippingNoticeRepository advancedShippingNoticeRepository;
    private final AdvancedShippingNoticeMapper advancedShippingNoticeMapper;
    private final PurchaseOrderMapper purchaseOrderMapper;

    /**
     * Get all advanced shipping notices.
     *
     * @see AdvancedShippingNoticeDto AdvancedShippingNoticeDto
     * @return List of all advanced shipping notices.
     */
    public List<AdvancedShippingNoticeDto> fetchAllAdvancedShippingNotices() {
        log.debug("Request to get all AdvancedShippingNotices");

        return advancedShippingNoticeRepository.findAll().stream()
                .map(advancedShippingNoticeMapper::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Get advanced shipping notice by ID.
     *
     * @see AdvancedShippingNoticeDto AdvancedShippingNoticeDto
     * @param id Advanced shipping notice ID.
     * @return Advanced shipping notice with specified ID.
     */
    public AdvancedShippingNoticeDto getAdvancedShippingNoticeById(long id) {
        log.debug("Request to get AdvancedShippingNotice by id : {}", id);

        Optional<AdvancedShippingNotice> advancedShippingNotice = advancedShippingNoticeRepository.findById(id);

        return advancedShippingNoticeMapper.toDto(advancedShippingNotice.orElseThrow(() ->
                new UserException(String.format("Could not find AdvancedShippingNotice with an ID: %s.", id), 404))
        );
    }

    /**
     * Create an advanced shipping notice.
     *
     * @see AdvancedShippingNoticeDto AdvancedShippingNoticeDto
     * @param advancedShippingNoticeDto Advanced shipping notice to create.
     * @return Created advanced shipping notice.
     */
    public AdvancedShippingNoticeDto createAdvancedShippingNotice(AdvancedShippingNoticeDto advancedShippingNoticeDto) {
        log.debug("Request to create an AdvancedShippingNotice: {}", advancedShippingNoticeDto);

        return advancedShippingNoticeMapper.toDto(
                advancedShippingNoticeRepository.save(
                        advancedShippingNoticeMapper.toEntity(advancedShippingNoticeDto)
                )
        );
    }

    /**
     * Add packages to an advanced shipping notice.
     *
     * @see com.knits.product.entity.PurchaseOrder PurchaseOrder
     * @see PurchaseOrderDto PurchaseOrderDto
     * @see AdvancedShippingNoticeDto AdvancedShippingNoticeDto
     * @param packages List of purchase orders (packages) to add to the advanced shipping notice.
     * @param id ID of an advanced shipping notice to add the packages to.
     * @return Advanced shipping notice with added packages.
     */
    public AdvancedShippingNoticeDto addPackagesToAdvancedShippingNotice(List<PurchaseOrderDto> packages, Long id) {
        log.debug("Request to add {} packages to AdvancedShippingNotice with an ID of {}", packages.size(), id);

        AdvancedShippingNotice advancedShippingNotice = advancedShippingNoticeRepository.findById(id).orElseThrow(() ->
                new UserException(String.format("Could not find AdvancedShippingNotice with an ID: %s.", id), 404));

        advancedShippingNotice.getPackages().addAll(
                packages.stream()
                        .map(purchaseOrderMapper::toPurchaseEntity)
                        .collect(Collectors.toList())
        );

        return advancedShippingNoticeMapper.toDto(advancedShippingNoticeRepository.save(advancedShippingNotice));
    }

    /**
     * Add packages to an advanced shipping notice.
     *
     * @see com.knits.product.entity.PurchaseOrder PurchaseOrder
     * @see PurchaseOrderDto PurchaseOrderDto
     * @see AdvancedShippingNoticeDto AdvancedShippingNoticeDto
     * @param packageId ID of a package that needs to be removed from the advanced shipping notice.
     * @param asnId ID of an advanced shipping notice to remove the package from.
     * @return Advanced shipping notice with the package removed.
     */
    public AdvancedShippingNoticeDto removePackageFromAdvancedShippingNoticeById(Long packageId, Long asnId) {
        log.debug("Request to remove a package with an ID: {} from AdvancedShippingNotice with an ID: {}.", packageId, asnId);

        AdvancedShippingNotice advancedShippingNotice = advancedShippingNoticeRepository.findById(asnId).orElseThrow(() ->
                new UserException(String.format("Could not find AdvancedShippingNotice with an ID: %s.", asnId), 404));

        advancedShippingNotice.getPackages().removeIf(it -> it.getId().equals(packageId));

        return advancedShippingNoticeMapper.toDto(advancedShippingNoticeRepository.save(advancedShippingNotice));
    }

    /**
     * Update advanced shipping notice.
     *
     * @see AdvancedShippingNoticeDto AdvancedShippingNoticeDto
     * @param advancedShippingNoticeDto Updated advanced shipping notice.
     * @return Updated advanced shipping notice.
     */
    public AdvancedShippingNoticeDto updateAdvancedShippingNotice(AdvancedShippingNoticeDto advancedShippingNoticeDto) {
        log.debug("Request to update AdvancedShippingNotice : {}", advancedShippingNoticeDto);

        AdvancedShippingNotice advancedShippingNotice = advancedShippingNoticeRepository.findById(advancedShippingNoticeDto.getId()).orElseThrow(() ->
                new UserException(String.format("Could not find AdvancedShippingNotice with an ID: %s.", advancedShippingNoticeDto.getId()), 404)
        );

        advancedShippingNoticeMapper.update(advancedShippingNotice, advancedShippingNoticeDto);

        return advancedShippingNoticeMapper.toDto(advancedShippingNoticeRepository.save(advancedShippingNotice));
    }

    /**
     * Partially update advanced shipping notice.
     *
     * @see AdvancedShippingNoticeDto AdvancedShippingNoticeDto
     * @param advancedShippingNoticeDto Partially updated advanced shipping notice.
     * @return Updated advanced shipping notice.
     */
    public AdvancedShippingNoticeDto partialUpdateAdvancedShippingNotice(AdvancedShippingNoticeDto advancedShippingNoticeDto) {
        log.debug("Request to partially update AdvancedShippingNotice : {}", advancedShippingNoticeDto);

        AdvancedShippingNotice advancedShippingNotice = advancedShippingNoticeRepository.findById(advancedShippingNoticeDto.getId()).orElseThrow(() ->
                new UserException(String.format("Could not find AdvancedShippingNotice with an ID: %s.", advancedShippingNoticeDto.getId()), 404)
        );

        advancedShippingNoticeMapper.partialUpdate(advancedShippingNotice, advancedShippingNoticeDto);

        return advancedShippingNoticeMapper.toDto(advancedShippingNoticeRepository.save(advancedShippingNotice));
    }
}
