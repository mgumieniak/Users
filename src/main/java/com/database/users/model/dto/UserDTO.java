package com.database.users.model.dto;

import lombok.Data;
import lombok.Value;

import java.time.LocalDate;

@Value
public class UserDTO {
    private final String name;
    private final String surname;
    private final String email;
    private final String phoneNumber;
    private final LocalDate creationAccountDate;
}
