package com.knits.product.controller;

import java.util.List;
import javax.validation.Valid;

import com.knits.product.dto.UpdateBookedAppointmentDto;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.knits.product.dto.UpdateBookedAppointment;
import com.knits.product.dto.AppointmentBookingDto;
import com.knits.product.service.AppointmentBookingService;

/**
 * This is REST API to handle creating appointment / modifying existing appointment
 * @author Soumen Banerjee
 */
@RestController
@AllArgsConstructor
@RequestMapping("api/appointments")
public class AppointmentBookingController {

    private final AppointmentBookingService appointmentBookingService;

    @GetMapping
    public ResponseEntity<List<AppointmentBookingDto>> getAllAppointments() {
        return ResponseEntity.ok().body(appointmentBookingService.getAllAppoitmentList());
    }

    @PatchMapping
    public ResponseEntity<List<AppointmentBookingDto>> changeBookingAppointment(@RequestBody UpdateBookedAppointment updateBookedAppointmentDto) {
        return ResponseEntity.ok().body(appointmentBookingService.changeAppointmentStatus(updateBookedAppointmentDto));
    }
}
