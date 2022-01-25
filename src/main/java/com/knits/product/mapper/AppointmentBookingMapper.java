package com.knits.product.mapper;

import org.mapstruct.Mapper;
import com.knits.product.dto.AppointmentBookingDto;
import com.knits.product.entity.AppointmentBooking;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface AppointmentBookingMapper {

    AppointmentBookingDto toDto(AppointmentBooking appointmentBooking);
    AppointmentBooking toEntity(AppointmentBookingDto appointmentBookingDto);
    void updateEntityFromDto(@MappingTarget AppointmentBooking appointmentBooking, AppointmentBookingDto appointmentBookingDto);
}
