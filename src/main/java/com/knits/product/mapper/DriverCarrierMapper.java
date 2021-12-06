package com.knits.product.mapper;

import java.util.List;
import org.mapstruct.Mapper;
import com.knits.product.dto.DriverDto;
import com.knits.product.entity.DriverCarrier;

@Mapper(componentModel = "spring")
public interface DriverCarrierMapper {

    List<DriverDto> toDriverDtoList(List<DriverCarrier> driverCarrerList);
    DriverCarrier toEntity(DriverDto driverDto);
}
