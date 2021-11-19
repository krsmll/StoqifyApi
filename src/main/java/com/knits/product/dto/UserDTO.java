package com.knits.product.dto;

import lombok.Data;

@Data
public class UserDTO {

    private Long id;
    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private Boolean active;
}
