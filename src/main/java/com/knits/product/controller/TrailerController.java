package com.knits.product.controller;

import com.knits.product.dto.TrailerDto;
import com.knits.product.service.TrailerService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * This is a REST API class
 *
 * @author Soumen Banerjee
 */
@RestController
@AllArgsConstructor
@RequestMapping("/api/trailer")
public class TrailerController {

    private final TrailerService trailerService;

    /**
     * @return list of trailers
     */
    @GetMapping
    public ResponseEntity<List<TrailerDto>> getAllTrailers() {
        return ResponseEntity.ok().body(trailerService.getTrailerData());
    }

    /**
     * @param trailerId to fetch trailer data
     * @return single trailer data by requested ID
     */
    @GetMapping(value = "/{id}")
    public ResponseEntity<TrailerDto> getTrailerDataById(@PathVariable("id") Long trailerId) {
        return ResponseEntity.ok().body(trailerService.getTrailerById(trailerId));
    }

    /**
     * @param trailerDto requested data to register trailer
     * @return void
     */
    @PostMapping
    public ResponseEntity<Void> registerTrailer(@Valid @RequestBody TrailerDto trailerDto) {
        trailerService.registerTrailer(trailerDto);
        return ResponseEntity.noContent().build();
    }

    /**
     * @param trailerId delete trailer by requested id
     * @return void
     */
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteTrailerById(@PathVariable("id") Long trailerId) {
        trailerService.deleteTrailerById(trailerId);
        return ResponseEntity.noContent().build();
    }

    /**
     * @param trailerDto requested trailer data to update trailer data
     * @return trailer updated trailer list
     */
    @PutMapping(value = "/update")
    public ResponseEntity<List<TrailerDto>> updateDriverList(@Valid @RequestBody TrailerDto trailerDto) {
        return ResponseEntity.ok().body(trailerService.updateTrailerData(trailerDto));
    }
}
