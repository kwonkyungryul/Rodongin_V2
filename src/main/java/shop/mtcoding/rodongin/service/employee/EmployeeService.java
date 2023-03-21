package shop.mtcoding.rodongin.service.employee;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import shop.mtcoding.rodongin.dto.employee.EmployeeReq.EmployeeJoinReqDto;
import shop.mtcoding.rodongin.dto.employee.EmployeeReq.EmployeeLoginReqDto;
import shop.mtcoding.rodongin.dto.employee.EmployeeReq.EmployeeUpdatdReq;
import shop.mtcoding.rodongin.handler.ex.CustomApiException;
import shop.mtcoding.rodongin.handler.ex.CustomException;
import shop.mtcoding.rodongin.model.employee.Employee;
import shop.mtcoding.rodongin.model.employee.EmployeeCareer;
import shop.mtcoding.rodongin.model.employee.EmployeeCareerRepository;
import shop.mtcoding.rodongin.model.employee.EmployeeGraduate;
import shop.mtcoding.rodongin.model.employee.EmployeeGraduateRepository;
import shop.mtcoding.rodongin.model.employee.EmployeeLicense;
import shop.mtcoding.rodongin.model.employee.EmployeeLicenseRepository;
import shop.mtcoding.rodongin.model.employee.EmployeeRepository;
import shop.mtcoding.rodongin.model.employee.EmployeeStack;
import shop.mtcoding.rodongin.model.employee.EmployeeStackRepository;
import shop.mtcoding.rodongin.util.Encode;
import shop.mtcoding.rodongin.util.PathUtil;

@Service
public class EmployeeService {
}
