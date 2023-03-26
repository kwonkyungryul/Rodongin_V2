package shop.mtcoding.rodongin.dto.apply;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import shop.mtcoding.rodongin.dto.employee.EmployeeDto;
import shop.mtcoding.rodongin.dto.resume.ResumeCareerDto;
import shop.mtcoding.rodongin.dto.resume.ResumeDto;
import shop.mtcoding.rodongin.dto.resume.ResumeStackDto;

@Getter
@Setter
@NoArgsConstructor
public class ApplyListOutDto {
   private Integer id;
   private ResumeDto resumeDto;
   private EmployeeDto employeeDto;
   private ResumeStackDto resumeStackDto;
   private ResumeCareerDto ResumeCareerDto;

}
