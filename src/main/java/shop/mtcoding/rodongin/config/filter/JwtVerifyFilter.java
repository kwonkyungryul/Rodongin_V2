package shop.mtcoding.rodongin.config.filter;

import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.http.HttpStatus;
import shop.mtcoding.rodongin.config.auth.JwtProvider;
import shop.mtcoding.rodongin.handler.ex.CustomApiException;
import shop.mtcoding.rodongin.model.company.Company;
import shop.mtcoding.rodongin.model.employee.Employee;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class JwtVerifyFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        String prefixJwt = req.getHeader(JwtProvider.HEADER);
        if (prefixJwt == null) {
            resp.setStatus(401);
            resp.setContentType("text/plain; charset=utf-8");
            resp.getWriter().println("인증이 되지 않았습니다.");
        } else {
            String jwt = prefixJwt.replace(JwtProvider.TOKEN_PREFIX, "");

            try {
                DecodedJWT decodedJWT = JwtProvider.verify(jwt);
                int id = decodedJWT.getClaim("id").asInt();
                String role = decodedJWT.getClaim("role").asString();

                HttpSession session = req.getSession();
                if (role.equals("employee")) {
                    Employee principal = Employee.builder().id(id).employeeRole(role).build();
                    session.setAttribute("principal", principal);
                }

                if (role.equals("company")) {
                    Company comPrincipal = Company.builder().id(id).companyRole(role).build();
                    session.setAttribute("comPrincipal", comPrincipal);
                }

                chain.doFilter(req, resp);
            } catch (SignatureVerificationException sve) {
                resp.setStatus(401);
                resp.setContentType("text/plain; charset=utf-8");
                resp.getWriter().println("인증이 되지 않았습니다.");
            } catch (TokenExpiredException tee) {
                resp.setStatus(401);
                resp.setContentType("text/plain; charset=utf-8");
                resp.getWriter().println("인증이 되지 않았습니다.");
            }
        }
    }
}
