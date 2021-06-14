package security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Crypto {
    public static String hashPasswordBcrypt(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }

    public static boolean matchesPasswords(String rawPassword, String encodedPassword) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.matches(rawPassword, encodedPassword);
    }
}
