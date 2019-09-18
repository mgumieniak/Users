package com.database.users.model.entity;

import com.database.users.model.dto.UserDTO;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@RequiredArgsConstructor
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
            this.creationAccountDate = creationAccountDate;
        }

        public Builder(User user, String userId, LocalDate creationAccountDate) {
            this.userId = userId;
            this.name = user.getName();
            this.surname = user.surname;
            this.email = user.email;
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

