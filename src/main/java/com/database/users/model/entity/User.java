package com.database.users.model.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@Document
public class User implements Serializable {
    @Id
    private String userId;
    private String name;
    private String surname;
    private String email;
    private String phoneNumber;
    private LocalDate creationAccountDate;
}

