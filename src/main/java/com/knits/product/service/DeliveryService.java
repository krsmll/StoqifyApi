package com.knits.product.service;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import com.knits.product.entity.*;
import java.util.stream.Collectors;
import java.util.concurrent.TimeUnit;
import com.knits.product.repository.*;
import org.springframework.stereotype.Service;
import com.knits.product.mapper.DeliveryMapper;
import com.knits.product.dto.DeliveryAssignDto;
import com.knits.product.dto.DeliveryProgressDto;
import com.knits.product.exceptions.UserException;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * This is a Delivery service class
 * @author Soumen Banerjee
 */
@Service("delivery")
public class DeliveryService {

    private Date end;
    private Date start;
    private Date arrivalTime;
    private DeliveryAssign deliveryAssign;
    private Boolean startAccepting = false;
    private Boolean finishAccepting = false;
    private List<DeliveryProgressDto> deliveryProgressList = new ArrayList<>();

    @Autowired
    private DeliveryMapper deliveryMapper;

    @Autowired
    private DeliveryAssignRepository deliveryAssignRepository;

    @Autowired
    private DeliveryProgressRepository deliveryProgressRepository;

    /**
     *
     * @return list of all assigned deliveries
     */
    public List<DeliveryAssign> getAllAssignedDeliveries() {

        return deliveryAssignRepository.findAll();
    }

    /**
     *
     * @param deliveryAssign requested delivery assigning data
     * @return full list of assigned deliveries data
     */
    public List<DeliveryAssign> assignNewDelivery(DeliveryAssignDto deliveryAssign) {

        this.arrivalTime = deliveryAssign.getArrivalTime() == null ? new Date() : deliveryAssign.getArrivalTime();

        deliveryAssign.setStatus("C");
        deliveryAssign.setArrivalTime(new Date());

        this.deliveryAssign = deliveryAssignRepository.save(deliveryMapper.toDeliveryAssignEntity(deliveryAssign));

        return getAllAssignedDeliveries();
    }

    /**
     *
     * @param deliveryProgressDto requested to fetch current progress of the delivery
     * @return list of all delivery progress
     */
    public List<DeliveryProgressDto> getDeliveryProgress(DeliveryProgressDto deliveryProgressDto) {
        if(deliveryProgressDto.getStatus().equals("E") && !finishAccepting && startAccepting) {
            this.end = new Date();
            this.startAccepting = false;
            this.finishAccepting = false;

            long diffInMillies = Math.abs(end.getTime() - start.getTime());
            long min = TimeUnit.MINUTES.convert(diffInMillies, TimeUnit.MILLISECONDS);
            long hours = TimeUnit.HOURS.convert(diffInMillies, TimeUnit.MILLISECONDS);
            long seconds = TimeUnit.SECONDS.convert(diffInMillies, TimeUnit.MILLISECONDS);


            DeliveryProgressDto fetchSingleDeliveryReport =
                    this.deliveryProgressList
                    .stream()
                    .filter(getDeliveryReport -> getDeliveryReport.getDeliveryId().equals(deliveryProgressDto.getDeliveryId()))
                    .findAny().orElseThrow(() -> new UserException("Delivery Report #" + deliveryProgressDto.getDeliveryId() + " does not exists"));

            int index = this.deliveryProgressList.indexOf(fetchSingleDeliveryReport);

            this.deliveryProgressList.remove(index);

            fetchSingleDeliveryReport.setStatus("E");
            fetchSingleDeliveryReport.setEndReceving(new Date());
            fetchSingleDeliveryReport.setComment("Ended Receving Process");
            fetchSingleDeliveryReport.setTotalTimeTaken(hours + "h: " + min + "m: " + seconds + "s");

            this.deliveryProgressList.add(fetchSingleDeliveryReport);

            deliveryProgressRepository.save(new DeliveryProgress(this.arrivalTime, fetchSingleDeliveryReport.getStartReceving(),
                    fetchSingleDeliveryReport.getEndReceving(), fetchSingleDeliveryReport.getTotalTimeTaken(),
                    fetchSingleDeliveryReport.getComment(), fetchSingleDeliveryReport.getStatus()));

            return this.deliveryProgressList;

        } else if(deliveryProgressDto.getStatus().equals("S") && !finishAccepting && !startAccepting) {
            this.startAccepting = true;
            this.start = new Date();
            this.deliveryProgressList.add(new DeliveryProgressDto(deliveryProgressDto.getDeliveryId() == null ?
                    this.deliveryAssign.getId() : deliveryProgressDto.getDeliveryId(), this.start, null,
                    "N/A", "Started", "S"));
        }

        return this.deliveryProgressList;
    }

    /**
     *
     * @return full list of stored delivery progress
     */
    public List<DeliveryProgressDto> getAllProgressReport() {
        return this.deliveryProgressRepository.findAll()
                .stream()
                .map(deliveryMapper::toDeliveryProgressDto)
                .distinct()
                .collect(Collectors.toList());
    }
}
