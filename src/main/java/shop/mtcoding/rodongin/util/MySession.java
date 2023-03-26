package shop.mtcoding.rodongin.util;

import javax.servlet.http.HttpSession;

import shop.mtcoding.rodongin.model.company.Company;
import shop.mtcoding.rodongin.model.employee.Employee;

public class MySession {
    public static Employee MyPrincipal(HttpSession session) {
        Employee emp = new Employee();
        emp.setId(1);
        emp.setEmployeeName("ssar");
        emp.setEmployeePassword("1234");
        session.setAttribute("principal", emp);
        Employee principal = (Employee) session.getAttribute("principal");
        return principal;
    }

    public static Company CompanyPrincipal(HttpSession session) {
        Company com = new Company();
        com.setId(1);
        com.setCompanyUsername("SAMSUNG");
        com.setCompanyPassword(
                "96e60856f35fafa9b394a64812f93659$ede46b1fc6024720c6b8690c57a9bc3d84ec705a88c5e081c7599500ebf15ce0");
        session.setAttribute("comPrincipal", com);
        Company comPrincipal = (Company) session.getAttribute("comPrincipal");
        return comPrincipal;
    }
}
