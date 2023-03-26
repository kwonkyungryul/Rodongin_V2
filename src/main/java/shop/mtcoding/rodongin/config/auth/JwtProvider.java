package shop.mtcoding.rodongin.config.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import shop.mtcoding.rodongin.model.company.Company;
import shop.mtcoding.rodongin.model.employee.Employee;

import java.util.Date;

public class JwtProvider {

    private static final String SUBJECT = "Rodongin";
    private static final int EXP = 1000*60*60;
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER = "Authorization";
    private static final String SECRET = System.getenv("JWT_SECRET");

    public static String create(Employee employee){
        String jwt = JWT.create()
                .withSubject(SUBJECT)
                .withExpiresAt(new Date(System.currentTimeMillis()+EXP))
                .withClaim("id", employee.getId())
                .withClaim("role", employee.getEmployeeRole())
                .sign(Algorithm.HMAC512(SECRET));
        return TOKEN_PREFIX + jwt;
    }

    public static String create(Company company){
        String jwt = JWT.create()
                .withSubject(SUBJECT)
                .withExpiresAt(new Date(System.currentTimeMillis()+EXP))
                .withClaim("id", company.getId())
                .withClaim("role", company.getCompanyRole())
                .sign(Algorithm.HMAC512(SECRET));
        return TOKEN_PREFIX + jwt;
    }

    public static DecodedJWT verify(String jwt) throws SignatureVerificationException, TokenExpiredException {

        DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC512(SECRET))
                .build().verify(jwt);
//        int id = decodedJWT.getClaim("id").asInt();
//        String role = decodedJWT.getClaim("role").asString();
//        System.out.println(id);
//        System.out.println(role);
        return decodedJWT;
    }
}
