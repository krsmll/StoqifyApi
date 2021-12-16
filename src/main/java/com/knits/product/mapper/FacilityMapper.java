package com.knits.product.mapper;

import java.util.List;
import org.mapstruct.Mapper;
import com.knits.product.entity.Facility;
import com.knits.product.dto.FacilityDto;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface FacilityMapper {

    List<FacilityDto> toFacilityDtoList(List<Facility> facilityList);
    Facility toFacilityEntity(FacilityDto facilityDto);
    FacilityDto toFacilityDto(Facility facility);
    void transformFacilityDataToEntity(@MappingTarget Facility facility, FacilityDto facilityDto);
}
