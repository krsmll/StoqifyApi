package com.knits.product.service;

import java.util.List;

import com.knits.product.dto.UpdateBookedAppointmentDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import com.knits.product.exceptions.UserException;
import com.knits.product.entity.AppointmentBooking;
import com.knits.product.dto.AppointmentBookingDto;
import com.knits.product.dto.UpdateBookedAppointment;
import com.knits.product.mapper.AppointmentBookingMapper;
import com.knits.product.repository.AppointmentBookingRepository;

/**
 * This is a appointment booking service class
 * @author Soumen Banerjee
 */
@Slf4j
@AllArgsConstructor
@Service("appointmentBooking")
public class AppointmentBookingService {

    private final AppointmentBookingMapper appointmentBookingMapper;
    private final AppointmentBookingRepository appointmentBookingRepository;

    /**
     *
     * @return list of booked appointment with carrier
     */
    public List<AppointmentBookingDto> getAllAppoitmentList() {
        return appointmentBookingRepository.findAll().stream().map(appointmentBookingMapper::toDto)
                .collect(Collectors.toList());
    }

    /**
     *
     * @param status change requested status for existing booking appointment
     * @param bookedAppointment requested booking id
     * @return
     */
    public List<AppointmentBookingDto> changeAppointmentStatus(UpdateBookedAppointment updateBookedAppointmentDto) {
        AppointmentBooking appointmentBooking = appointmentBookingRepository.findById(updateBookedAppointmentDto.getId())
                .orElseThrow(() -> new UserException("Appointment #" + updateBookedAppointmentDto.getId() + " does not exists."));

        if(updateBookedAppointmentDto.getStatus().equals("A")) {
            appointmentBooking.setStatus("A");
        } else if(updateBookedAppointmentDto.getStatus().equals("R")) {
            appointmentBooking.setStatus("R");
        } else if(updateBookedAppointmentDto.getStatus().equals("D")) {
            appointmentBooking.setStatus("D");
        }

        appointmentBooking.setComment(updateBookedAppointmentDto.getComment());
        appointmentBookingRepository.save(appointmentBooking);
        return appointmentBookingRepository.findAll().stream().map(appointmentBookingMapper::toDto).collect(Collectors.toList());
    }
}
