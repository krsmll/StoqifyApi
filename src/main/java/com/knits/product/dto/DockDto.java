package com.knits.product.dto;

import lombok.Data;
import java.util.Date;
import javax.validation.constraints.Pattern;
import com.knits.product.dto.groups.InsertGroup;

@Data
public class DockDto {

    private Long id;

    @Pattern(regexp = "^[0-9]*$", message = "Supervisor ID should contain only number",
            groups = InsertGroup.class)
    private Long supervisorId;

    @Pattern(regexp = "^[0-9]*$", message = "Dock dimention should contain only number")
    private Long dockDimentaion;

    @Pattern(regexp = "^[A-Za-z]*$",
            message = "Status should contain only characters",
            groups = InsertGroup.class)
    private String status;

    @Pattern(regexp = "^[A-Za-z]*$", message = "Comment should contain only characters")
    private String comment;
    private Date created;

    @Pattern(regexp = "^[0-9]*$", message = "Item count should contain only number",
            groups = InsertGroup.class)
    private Integer itemCount;

    private Date reservationDate;
    private Date preRegistration;
    private Date lastUpdated;
}
