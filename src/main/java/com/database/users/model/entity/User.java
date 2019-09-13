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
    private final String userId;
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
        private final String userId;
        private final String name;
        private final String surname;
        private final String email;
        private final LocalDate creationAccountDate;

        private String phoneNumber = "";

        public Builder(String userId, String name, String surname, String email, String phoneNumber, LocalDate creationAccountDate) {
            this.userId = userId;
            this.name = name;
            this.surname = surname;
            this.email = email;
            this.phoneNumber = phoneNumber;
            this.creationAccountDate = creationAccountDate;
        }

        public Builder phoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public com.database.users.model.entity.User build() {
            return new com.database.users.model.entity.User(this);
        }
    }
}

