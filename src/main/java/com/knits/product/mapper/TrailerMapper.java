package com.knits.product.mapper;

import java.util.List;
import org.mapstruct.Mapper;
import com.knits.product.dto.TrailerDto;
import com.knits.product.entity.Trailer;
import org.mapstruct.MappingTarget;

/**
 * This is a mapper interface to map entity
 * @author Soumen Banerjee
 */
@Mapper(componentModel = "spring")
public interface TrailerMapper {

    TrailerDto toTrailerDto(Trailer trailer);
    Trailer toTrailerEntity(TrailerDto trailerDto);
    List<TrailerDto> toTrailerDtoList(List<Trailer> trailerDtoList);
    void toUpdatedTrailerDtoList(@MappingTarget Trailer trailer, TrailerDto trailerDto);
}
