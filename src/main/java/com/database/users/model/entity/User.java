package com.database.users.model.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@Document
public class User {
    @Id
    private String userId;

    private String name;
    private String surname;
    private String email;
    private String phoneNumber;
    private LocalDate creationAccountDate;


    private User(Builder builder) {
        userId = builder.userId;
        name = builder.name;
        surname = builder.surname;
        email = builder.email;
        phoneNumber = builder.phoneNumber;
        creationAccountDate = builder.creationAccountDate;
    }

    public static class Builder {
        private final String name;
        private final String surname;
        private final String email;
        private final LocalDate creationAccountDate;

        private String userId = null;
        private String phoneNumber = "";

        public Builder(String name, String surname, String email, LocalDate creationAccountDate) {
            this.name = name;
            this.surname = surname;
            this.email = email;
            this.creationAccountDate = creationAccountDate;
        }

        public Builder userId(String userId) {
            this.userId = userId;
            return this;
        }

        public Builder phoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}

