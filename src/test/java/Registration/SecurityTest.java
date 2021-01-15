package Registration;

import org.junit.jupiter.api.Test;
import security.Crypto;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SecurityTest {
    @Test
    public void checkCrypto() {
        String rawPassword = "qwerty007";
        String hashedPassword = Crypto.hashPasswordBcrypt(rawPassword);
        assertTrue(Crypto.matchesPasswords(rawPassword, hashedPassword));
    }
}
