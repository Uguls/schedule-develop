package com.sparta.schedule_develop.util;

import at.favre.lib.crypto.bcrypt.BCrypt;

public class PasswordUtil {

    public static String encode(String rawPassword) {
        return BCrypt.withDefaults().hashToString(12, rawPassword.toCharArray());
    }

    public static boolean matches(String rawPassword, String hashedPassword) {
        return BCrypt.verifyer().verify(rawPassword.toCharArray(), hashedPassword).verified;
    }

}
