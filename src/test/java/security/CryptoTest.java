package security;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CryptoTest {

    @Test
    public void hashPassword() {
        String rawPassword = "qwerty007";
        String hashedPassword = Crypto.hashPasswordBcrypt(rawPassword);
        assertTrue(Crypto.matchesPasswords(rawPassword, hashedPassword));

        String emptyPassword = "";
        String hashedEmptyPassword = Crypto.hashPasswordBcrypt(emptyPassword);
        assertTrue(Crypto.matchesPasswords(emptyPassword, hashedEmptyPassword));
    }

    @Test
    public void matches() {
        assertFalse(Crypto.matchesPasswords("", ""));
        assertFalse(Crypto.matchesPasswords("hi", "hi"));
    }
}
