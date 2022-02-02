package com.knits.product.controller;

import com.knits.product.dto.AppointmentBookingAssignASNDto;
import com.knits.product.dto.AppointmentBookingDto;
import com.knits.product.dto.UpdateBookedAppointment;
import com.knits.product.service.AppointmentBookingService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * This is REST API to handle creating appointment / modifying existing appointment
 *
 * @author Soumen Banerjee
 */
@RestController
@AllArgsConstructor
@RequestMapping("api/bookappointments")
public class AppointmentBookingController {

    private final AppointmentBookingService appointmentBookingService;

    @GetMapping
    public ResponseEntity<List<AppointmentBookingDto>> getAllAppointments() {
        return ResponseEntity.ok().body(appointmentBookingService.getAllAppoitmentList());
    }

    @PutMapping
    public ResponseEntity<List<AppointmentBookingDto>> changeBookingAppointment(@RequestBody UpdateBookedAppointment updateBookedAppointmentDto) {
        return ResponseEntity.ok().body(appointmentBookingService.changeAppointmentStatus(updateBookedAppointmentDto));
    }

    @PostMapping("/assignasn")
    public ResponseEntity<List<AppointmentBookingDto>> assignASNtoNewlyCreatedBooking(@Valid @RequestBody AppointmentBookingAssignASNDto appointmentBookingAssignASNDto) {
        return ResponseEntity.ok().body(appointmentBookingService.assignBookingWithASN(appointmentBookingAssignASNDto));
    }

    @PostMapping("/newbooking")
    public ResponseEntity<List<AppointmentBookingDto>> createNewBookingAppointment(@RequestBody AppointmentBookingDto appointmentBookingDto) {
        return new ResponseEntity<>(appointmentBookingService.createNewAppointment(appointmentBookingDto), HttpStatus.CREATED);
    }
}
