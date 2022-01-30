package com.knits.product.dto;

import com.knits.product.dto.groups.InsertGroup;
import com.knits.product.dto.groups.UpdateGroup;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.Date;
import java.util.List;

/**
 * Data transfer object for the advanced shipping notice entity.
 * @see com.knits.product.entity.AdvancedShippingNotice AdvancedShippingNotice
 */
@Data
public class AdvancedShippingNoticeDto {
    @NotNull(groups = {UpdateGroup.class})
    private Long id;

    @NotBlank(groups = {InsertGroup.class})
    private String billOfLandingNumber;

    @Null
    private Date shipmentDate;

    private Date deliveryDate;

    @NotBlank(groups = {InsertGroup.class})
    private String identificationNumber;

    @NotBlank(groups = {InsertGroup.class})
    private String status;

    @NotNull(groups = {InsertGroup.class})
    private FacilityDto fromFacility;

    @NotNull(groups = {InsertGroup.class})
    private FacilityDto toFacility;

    @NotNull(groups = {InsertGroup.class})
    private SupplierDto supplier;

    @NotNull(groups = {InsertGroup.class})
    private CustomerDto customer;

    @NotNull(groups = {InsertGroup.class})
    private DriverDto driver;

    @NotNull(groups = {InsertGroup.class})
    private TrailerDto trailer;

    @NotNull(groups = {InsertGroup.class})
    private List<PurchaseOrderDto> packages;
}
