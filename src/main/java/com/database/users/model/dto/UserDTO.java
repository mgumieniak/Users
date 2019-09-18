package com.database.users.model.dto;

import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@RequiredArgsConstructor
public class UserDTO {
    private final String name;
    private final String surname;
    private final String email;
    private final String phoneNumber;
    private final LocalDate creationAccountDate;
}
