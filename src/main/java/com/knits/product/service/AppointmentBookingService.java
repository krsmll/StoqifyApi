package com.knits.product.service;

import java.util.Date;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import lombok.AllArgsConstructor;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import com.knits.product.exceptions.UserException;
import com.knits.product.entity.AppointmentBooking;
import com.knits.product.dto.AppointmentBookingDto;
import com.knits.product.dto.UpdateBookedAppointment;
import com.knits.product.mapper.AppointmentBookingMapper;
import com.knits.product.dto.AppointmentBookingAssignASNDto;
import com.knits.product.repository.AppointmentBookingRepository;
import com.knits.product.entity.AppointBookingWithAdvancedShipping;
import com.knits.product.repository.AppointmentBookingWithAdvancedShippingRepository;

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
    private final AppointmentBookingWithAdvancedShippingRepository appointmentBookingWithAdvancedShippingRepository;

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

    /**
     *
     * @param appointmentBookingAssignASNDto
     * @return list of bookings with ASN data after assigned
     */
    public List<AppointmentBookingDto> assignBookingWithASN(AppointmentBookingAssignASNDto appointmentBookingAssignASNDto) {
        appointmentBookingWithAdvancedShippingRepository.save(new AppointBookingWithAdvancedShipping(0L, appointmentBookingAssignASNDto.getAppointmentId(), appointmentBookingAssignASNDto.getAsnId()));
        return getAllAppoitmentList();
    }

    /**
     *
     * @param appointmentBookingDto requested appointment data
     * @return list of booked appointments after creating a new appointment
     */
    public List<AppointmentBookingDto> createNewAppointment(AppointmentBookingDto appointmentBookingDto) {
        AppointmentBooking newAppointmentBooking = appointmentBookingMapper.toEntity(appointmentBookingDto);

        newAppointmentBooking.setStatus("C");
        newAppointmentBooking.setComment("New booking created");
        newAppointmentBooking.setCreatedData(new Date());

        appointmentBookingRepository.save(appointmentBookingMapper.toEntity(appointmentBookingDto));
        return appointmentBookingRepository.findAll().stream()
                .map(appointmentBookingMapper::toDto).collect(Collectors.toList());
    }
}
