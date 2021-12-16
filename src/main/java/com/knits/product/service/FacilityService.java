package com.knits.product.service;

import java.util.List;
import lombok.AllArgsConstructor;
import com.knits.product.dto.FacilityDto;
import com.knits.product.entity.Facility;
import org.springframework.stereotype.Service;
import com.knits.product.mapper.FacilityMapper;
import com.knits.product.exceptions.UserException;
import com.knits.product.repository.FacilityRepository;

/**
 * This is facility Service class to handle facility data
 * @author Soumen Banerjee
 */
@Service("facility")
@AllArgsConstructor
public class FacilityService {

    private final FacilityMapper facilityMapper;
    private final FacilityRepository facilityRepository;

    /**
     *
     * @return all facility data list
     */
    public List<FacilityDto> getAllFacilityData() {
        return facilityMapper.toFacilityDtoList(facilityRepository.findAll());
    }

    /**
     *
     * @param facilityDto requested facility data to save
     * @return facility list after saving
     */
    public List<FacilityDto> saveFacilityData(FacilityDto facilityDto) {
        facilityRepository.save(facilityMapper.toFacilityEntity(facilityDto));
        return facilityMapper.toFacilityDtoList(facilityRepository.findAll());
    }

    /**
     *
     * @param facilityDto requested faciliy data to edit
     * @return list faiclity list after edit
     */
    public List<FacilityDto> editFacilityData(FacilityDto facilityDto) {
        Facility getFacilityData = facilityRepository.findById(facilityDto.getId())
                .orElseThrow(() -> new UserException("Facility# " + facilityDto.getId() + " not found"));
        facilityMapper.transformFacilityDataToEntity(getFacilityData, facilityDto);
        facilityRepository.save(getFacilityData);

        return facilityMapper.toFacilityDtoList(facilityRepository.findAll());
    }

    /**
     *
     * @param facilitySearchedWord search word
     * @return facility dto
     */
    public FacilityDto getFacilitySearchedData(String facilitySearchedWord) {
        return facilityMapper.toFacilityDto(facilityRepository.findByNameStartsWithIgnoreCase(facilitySearchedWord)
                .orElseThrow(() -> new UserException("Facility# " + facilitySearchedWord + " by word not found")));
    }

    /**
     *
     * @param facilityDto requested facility id  to deactive
     * @return returnn all facility data after deactivation
     */
    public List<FacilityDto> deactivateFacilityData(FacilityDto facilityDto) {
        Facility getFacilityData = facilityRepository.findById(facilityDto.getId())
                .orElseThrow(() -> new UserException("Facility #" + facilityDto.getId() + " not found"));

        getFacilityData.setStatus(false);
        facilityRepository.save(getFacilityData);

        return facilityMapper.toFacilityDtoList(facilityRepository.findAll());
    }
}
