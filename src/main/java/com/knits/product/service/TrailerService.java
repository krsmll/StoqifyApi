package com.knits.product.service;

import java.util.List;
import lombok.AllArgsConstructor;
import com.knits.product.dto.TrailerDto;
import com.knits.product.entity.Trailer;
import org.springframework.stereotype.Service;
import com.knits.product.mapper.TrailerMapper;
import com.knits.product.exceptions.UserException;
import com.knits.product.repository.TrailerRepository;

/**
 * This is a service class which is responsible to process trailer data
 * @author Soumen Banerjee
 */
@Service("trailer")
@AllArgsConstructor
public class TrailerService {

    private final TrailerMapper trailerMapper;
    private final TrailerRepository trailerRepository;

    /**
     *
     * @return trailer list
     */
    public List<TrailerDto> getTrailerData() {
        List<TrailerDto> trailerData = trailerMapper.toTrailerDtoList(trailerRepository.findAll());

        return trailerData;
    }

    /**
     *
     * @param trailerId to fetch single trailer data
     * @return trailer data by trailer id
     */
    public TrailerDto getTrailerById(Long trailerId) {
        Trailer getTrailerDataById = trailerRepository.findById(trailerId)
                .orElseThrow(() -> new UserException("Trailer# " + trailerId + " not found"));

        return trailerMapper.toTrailerDto(getTrailerDataById);
    }

    /**
     *
     * @param trailerDto requested data via trailer dto to register a trailer
     */
    public void registerTrailer(TrailerDto trailerDto) {
        trailerRepository.save(trailerMapper.toTrailerEntity(trailerDto));
    }

    /**
     *
     * @param trailerId delete trailer by requested id
     */
    public void deleteTrailerById(Long trailerId) {
        trailerRepository.deleteById(trailerId);
    }

    /**
     * @param trailerDto requested data to change trailer data
     * @return trailer list
     */
    public List<TrailerDto> updateTrailerData(TrailerDto trailerDto) {
        Trailer getTrailerData = trailerRepository.findById(trailerDto.getId())
                .orElseThrow(() -> new UserException("Trailer# " + trailerDto.getId() + " not found"));

        trailerMapper.toUpdatedTrailerDtoList(getTrailerData, trailerDto);
        trailerRepository.save(getTrailerData);
        return trailerMapper.toTrailerDtoList(trailerRepository.findAll());
    }
}
