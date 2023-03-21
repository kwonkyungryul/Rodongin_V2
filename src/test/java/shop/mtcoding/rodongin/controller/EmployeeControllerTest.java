package shop.mtcoding.rodongin.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import shop.mtcoding.rodongin.dto.employee.EmployeeReq.EmployeeUpdatdReq;
import shop.mtcoding.rodongin.model.employee.Employee;

@Transactional // 메서드 실행 직후 롤백!! // auto_increment 초기화
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper om;

    private MockHttpSession mockSession;

    @BeforeEach
    public void setUp() {
        // 세션 주입
        Employee employee = new Employee();
        employee.setId(1);
        employee.setEmployeeName("ssar");
        employee.setEmployeePassword("1234");
        employee.setEmployeeEmail("ssar@nate.com");
        // employee.setEmployeeBirth(date);
        employee.setEmployeeTel("01011111111");
        employee.setEmployeeAddress("서울특별시 강남구");

        mockSession = new MockHttpSession();
        mockSession.setAttribute("principal", employee);
    }

    @Test
    public void update_test() throws Exception {
        // given

        Date date = new Date(1990 - 01 - 12);
        int id = 1;
        EmployeeUpdatdReq employeeUpdatdReq = new EmployeeUpdatdReq();
        employeeUpdatdReq.setEmployeePassword("1234");
        employeeUpdatdReq.setEmployeeEmail("ssar@nate.com");
        employeeUpdatdReq.setEmployeeBirth(date);
        employeeUpdatdReq.setEmployeeTel("01022222222");
        employeeUpdatdReq.setEmployeeAddress("부산 진구 부전동");

        String requestBody = om.writeValueAsString(employeeUpdatdReq);
        System.out.println("테스트 : " + requestBody);

        // when
        ResultActions resultActions = mvc.perform(
                put("/employee/" + id)
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .session(mockSession));

        // then
        resultActions.andExpect(status().isOk());
        // resultActions.andExpect(jsonPath("$.code").value(1));
    }

}
