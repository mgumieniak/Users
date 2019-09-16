package com.database.users.model.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UserDTO {
    private String name;
    private String surname;
    private String email;
    private String phoneNumber;
    private LocalDate creationAccountDate;
}
