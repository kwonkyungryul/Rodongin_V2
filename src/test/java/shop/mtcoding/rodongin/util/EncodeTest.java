package shop.mtcoding.rodongin.util;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.security.NoSuchAlgorithmException;

import org.junit.jupiter.api.Test;

public class EncodeTest {
    @Test
    public void testPasswordEncodeOutput() throws NoSuchAlgorithmException {
        String password = "1234";
        String encodedPassword = Encode.passwordEncode(password);

        // 반환된 문자열이 null이 아니고 비어 있지 않은지 확인합니다.
        assertNotNull(encodedPassword);
        assertNotEquals("", encodedPassword);
     

        // 반환된 문자열을 출력합니다.
        System.out.println(encodedPassword);
    }
}
