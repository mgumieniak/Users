package com.database.users.error;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@RequiredArgsConstructor
@Getter
class Error {
    private final int code;
    private final String msg;
    private final String ex;

    public static Error createError(int code,
                                    String msg,
                                    String ex) {
        return new Error(code, msg, ex);
    }
}
