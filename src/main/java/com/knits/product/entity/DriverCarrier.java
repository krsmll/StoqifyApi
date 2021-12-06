package com.knits.product.entity;

import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name = "driver_carrier")
public class DriverCarrier {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false, length = 128)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 128)
    private String lastName;

    @Column(name = "driving_licence_number", nullable = false, length = 128)
    private String drivingLicenceNumber;

    @Column(name = "phone_no", nullable = false, length = 128)
    private String phoneNo;

    @Column(name = "driver_address", nullable = false, length = 128)
    private String driverAddress;

    @Column(name = "carrier_company_name", nullable = false, length = 128)
    private String carrierCompanyName;
}
