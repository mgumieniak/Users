package com.database.users.auth;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class User {
    private final UUID userId;
    private final UUID vwId;

    private User(UUID userId, UUID vwId) {
        this.userId = userId;
        this.vwId = vwId;
    }

    public static User createUser(UUID userId, UUID vwId) {
        return new User(userId, vwId);
    }
}
