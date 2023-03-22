package shop.mtcoding.rodongin.service.employee;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.rodongin.dto.employee.*;
import shop.mtcoding.rodongin.dto.resume.ResumeDto;
import shop.mtcoding.rodongin.model.employee.*;
import shop.mtcoding.rodongin.model.resume.ResumeRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    private final EmployeeGraduateRepository employeeGraduateRepository;

    private final EmployeeCareerRepository employeeCareerRepository;

    private final EmployeeLicenseRepository employeeLicenseRepository;

    private final EmployeeStackRepository employeeStackRepository;

    private final ResumeRepository resumeRepository;


    @Transactional(readOnly = true)
    public EmployeeDetailOutDto 유저정보조회(int principalId) {
        Employee employee = employeeRepository.findById(principalId);
        List<EmployeeGraduateDto> graduates = employeeGraduateRepository.findByEmpId(principalId);
        List<EmployeeCareerDto> careers = employeeCareerRepository.findByEmpId(principalId);
        List<EmployeeLicenseDto> licenses = employeeLicenseRepository.findByEmpId(principalId);
        List<EmployeeStackDto> stacks = employeeStackRepository.findByEmpId(principalId);
        List<ResumeDto> resumes = resumeRepository.findByEmpId(principalId);


        EmployeeDetailOutDto employeeDetailInfo = EmployeeDetailOutDto.builder()
                .id(employee.getId())
                .employeeFullname(employee.getEmployeeFullname())
                .employeeName(employee.getEmployeeName())
                .employeeAddress(employee.getEmployeeAddress())
                .employeeBirth(employee.getEmployeeBirth())
                .employeeEmail(employee.getEmployeeEmail())
                .employeeTel(employee.getEmployeeTel())
                .employeeGraduateDtos(graduates)
                .employeeCareerDtos(careers)
                .employeeLicenseDtos(licenses)
                .employeeStackDtos(stacks)
                .resumeDtos(resumes)
                .build();

        return employeeDetailInfo;
    }
}
