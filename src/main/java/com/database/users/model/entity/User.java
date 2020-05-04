package com.database.users.model.entity;

import com.database.models.security.Permissions;
import com.database.models.security.Roles;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private final Long userId;

    private final String name;
    private final String surname;
    private final String email;
    private final String phoneNumber;
    private final Roles roles;
    private final Permissions permissions;
    private final LocalDate creationAccountDate;


    private User(Builder builder) {
        userId = builder.userId;
        name = builder.name;
        surname = builder.surname;
        email = builder.email;
        phoneNumber = builder.phoneNumber;
        roles = builder.roles;
        permissions = builder.permissions;
        creationAccountDate = builder.creationAccountDate;
    }

    public static class Builder {
        private final String name;
        private final String surname;
        private final String email;
        private final LocalDate creationAccountDate;

        private Long userId = null;
        private Roles roles = Roles.USER;
        private Permissions permissions = Permissions.STANDARD;
        private String phoneNumber = "";

        public Builder(String name, String surname, String email, LocalDate creationAccountDate) {
            this.name = name;
            this.surname = surname;
            this.email = email;
            this.creationAccountDate = creationAccountDate;
        }

        public Builder userId(Long userId) {
            this.userId = userId;
            return this;
        }

        public Builder phoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public Builder roles(Roles roles) {
            this.roles = roles;
            return this;
        }

        public Builder permissions(Permissions permissions) {
            this.permissions = permissions;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}

