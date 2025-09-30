package com.calculator.deepmarket.liberia;

//importamos argon para encriptar contraseñas
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;

public class PasswordUtils {
    private static final Argon2PasswordEncoder encoder =
            new Argon2PasswordEncoder(
                    16,     // saltLength
                    32,     // hashLength
                    1,      // parallelism
                    1 << 12, // memory (4096 KB = 4 MB)
                    3       // iterations
            );

    /**
     * Hashea la contraseña con Argon2id
     */
    public static String hash(String rawPassword) {
        return encoder.encode(rawPassword);
    }

    /**
     * Verifica si la contraseña en texto plano coincide con el hash
     */
    public static boolean matches(String rawPassword, String hashedPassword) {
        return encoder.matches(rawPassword, hashedPassword);
    }
}

