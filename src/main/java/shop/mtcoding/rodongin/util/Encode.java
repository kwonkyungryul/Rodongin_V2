package shop.mtcoding.rodongin.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;

public class Encode {

    private static final int ITERATIONS = 10000; // 스트레칭 횟수
    private static final int SALT_LENGTH = 16; // 솔팅 값의 길이

    public static String passwordEncode(String password) throws NoSuchAlgorithmException {
        byte[] salt = generateSalt(); // 솔팅 값 생성
        byte[] hashedPassword = hashPassword(password, salt); // 비밀번호 해싱
        return String.format("%s$%s", bytesToHex(salt), bytesToHex(hashedPassword)); // 솔트 값과 해싱된 비밀번호를 저장
    }

    public static boolean matches(String password, String encodedPassword) throws NoSuchAlgorithmException {
        String[] parts = encodedPassword.split("\\$"); // 솔트 값과 해싱된 비밀번호 분리
        byte[] salt = hexToBytes(parts[0]);
        byte[] hashedPassword = hashPassword(password, salt);
        String encodedHashedPassword = String.format("%s$%s", bytesToHex(salt), bytesToHex(hashedPassword));
        return encodedPassword.equals(encodedHashedPassword); // 인코딩된 비밀번호와 일치하는지 검사
    }

    private static byte[] generateSalt() {
        byte[] salt = new byte[SALT_LENGTH];
        new SecureRandom().nextBytes(salt); // 보안적으로 안전한 난수 생성기 사용
        return salt;
    }

    private static byte[] hashPassword(String password, byte[] salt) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        digest.reset();
        digest.update(salt); // 솔트 값을 해시에 추가
        byte[] input = digest.digest(password.getBytes());
        for (int i = 0; i < ITERATIONS; i++) {
            digest.reset();
            input = digest.digest(concatenate(input, salt)); // 스트레칭 적용
        }
        return input;
    }

    private static byte[] concatenate(byte[] input1, byte[] input2) {
        byte[] result = Arrays.copyOf(input1, input1.length + input2.length);
        System.arraycopy(input2, 0, result, input1.length, input2.length);
        return result;
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    private static byte[] hexToBytes(String hex) {
        int length = hex.length();
        byte[] bytes = new byte[length / 2];
        for (int i = 0; i < length; i += 2) {
            bytes[i / 2] = (byte) ((Character.digit(hex.charAt(i), 16) << 4)
                    + Character.digit(hex.charAt(i + 1), 16));
        }
        return bytes;
    }
}
