package shop.mtcoding.rodongin.util;


import org.junit.jupiter.api.Test;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;

public class JwtSecretKeyGeneratorTest {
    private final int BYTE_LENGTH = 64;

    @Test
    public void generateRandomBytes() {
        byte[] bytes = new byte[BYTE_LENGTH];
        new SecureRandom().nextBytes(bytes);
        System.out.println("테스트 : " + bytes);

        String encodedKey = Base64.getEncoder().encodeToString(bytes);
        System.out.println("JWT Secret Key: " + encodedKey);

        byte[] decodedKey = Base64.getDecoder().decode(encodedKey);
        System.out.println("Decoded Key : " + decodedKey); // ? 왜 값이 다름

        System.out.println(Arrays.equals(bytes, decodedKey)); // 근데 왜 true ?
    }
}
