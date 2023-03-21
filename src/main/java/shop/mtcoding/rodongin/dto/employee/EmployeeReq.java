package shop.mtcoding.rodongin.dto.employee;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

public class EmployeeReq {

    @Setter
    @Getter
    public static class EmployeeLoginReqDto {
        private String employeeName;
        private String employeePassword;
    }

    @Setter
    @Getter
    public static class EmployeeJoinReqDto {
        private String employeeName;
        private String employeePassword;
        private String employeeEmail;
        private String employeeFullname;
        private Date employeeBirth;
        private String employeeTel;
        private String employeeGender;
        private String employeeAddress;
    }

    @Getter
    @Setter
    public static class EmployeeUpdatdReq {
        private Integer id;
        private String employeePassword;
        private String employeeEmail;
        private Date employeeBirth;
        private String employeeTel;
        private String employeeAddress;
        // private String employeeThumbnail;

    }
}
