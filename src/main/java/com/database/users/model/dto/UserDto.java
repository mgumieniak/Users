package com.database.users.model.dto;

import com.database.models.security.Permissions;
import com.database.models.security.Roles;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.Email;
import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@RequiredArgsConstructor
public class UserDto {
    private final String name;
    private final String surname;
    @Email
    private final String email;
    private final String phoneNumber;
    private final String roles;
    private final String permissions;
    private final LocalDate creationAccountDate;

    private UserDto(Builder builder) {
        name = builder.name;
        surname = builder.surname;
        email = builder.email;
        phoneNumber = builder.phoneNumber;
        roles = builder.roles;
        permissions = builder.permissions;
        creationAccountDate = builder.creationAccountDate;
    }

    public static class Builder {
        private final LocalDate creationAccountDate;

        private String name = "";
        private String surname = "";
        private String email = "";
        private String phoneNumber = "";
        private String roles = Roles.USER.toString();
        private String permissions = Permissions.STANDARD.toString();

        public Builder(LocalDate creationAccountDate) {
            this.creationAccountDate = creationAccountDate;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder surname(String surname) {
            this.surname = surname;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder phoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public Builder roles(String roles) {
            this.roles = roles;
            return this;
        }

        public Builder permissions(String permissions) {
            this.permissions = permissions;
            return this;
        }

        public UserDto build() {
            return new UserDto(this);
        }
    }
}
