package shop.mtcoding.rodongin.handler;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import shop.mtcoding.rodongin.handler.ex.CustomException;

@RestControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public String customException(Exception e) {
        StringBuilder sb = new StringBuilder();
        sb.append("<script>");
        sb.append("alert('" + e.getMessage() + "');");
        sb.append("history.back();");
        sb.append("</script>");
        return sb.toString();
    }

}
