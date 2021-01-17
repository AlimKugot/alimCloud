package security;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CryptoTest {
    @Test
    public void checkCrypto() {
        String rawPassword = "qwerty007";
        String hashedPassword = Crypto.hashPasswordBcrypt(rawPassword);
        assertTrue(Crypto.matchesPasswords(rawPassword, hashedPassword));
    }
}
