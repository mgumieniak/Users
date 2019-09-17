package com.database.users.model.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDate;

@Value
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@Document
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private String userId;
    private final String name;
    private final String surname;
    private final String email;
    private final String phoneNumber;
    private final LocalDate creationAccountDate;

    public User(Builder builder) {
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

        private String userId = "";
        private String phoneNumber = "";

        public Builder(String name, String surname, String email, String phoneNumber, LocalDate creationAccountDate) {
            this.name = name;
            this.surname = surname;
            this.email = email;
            this.phoneNumber = phoneNumber;
            this.creationAccountDate = creationAccountDate;
        }

        public Builder(User user, String userId, LocalDate creationAccountDate) {
            this.userId = userId;
            this.name = user.getName();
            this.surname = user.surname;
            this.email = user.email;
            this.phoneNumber = user.phoneNumber;
            this.creationAccountDate = creationAccountDate;
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

