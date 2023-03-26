package shop.mtcoding.rodongin.util;

import org.junit.jupiter.api.Test;

public class EnvTest {

    @Test
    public void env_test() {
        String secret = System.getenv("JWT_SECRET");
        System.out.println(secret);
    }
}
