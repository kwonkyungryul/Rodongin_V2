package shop.mtcoding.rodongin.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Date;

import javax.servlet.http.HttpSession;

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

    // @BeforeEach
    // public void setUp() {
    // // 세션 주입
    // Employee employee = new Employee();
    // employee.setId(1);
    // employee.setEmployeeName("ssar");
    // employee.setEmployeePassword("1234");
    // employee.setEmployeeEmail("ssar@nate.com");
    // // employee.setEmployeeBirth(date);
    // employee.setEmployeeTel("01011111111");
    // employee.setEmployeeAddress("서울특별시 강남구");

    // mockSession = new MockHttpSession();
    // mockSession.setAttribute("principal", employee);
    // }

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

    @Test
    public void join_test() throws Exception {
        // given
        String requestBody = "employeeName=asdfsdfsdfr&employeePassword=1234&employeeEmail=love@naver.com&employeeFullname=LOVE&employeeBirth=1990-01-01&employeeTel=01011111111&employeeGender=M&employeeAddress=서울특별시 강남구";

        // when
        ResultActions resultActions = mvc.perform(post("/employee/join").content(requestBody)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE));

        // then
        resultActions.andExpect(status().isCreated());
    }

    @Test
    public void login_test() throws Exception {
        // given
        String requestBody = "employeeName=ssar&employeePassword=1234";

        // when
        ResultActions resultActions = mvc.perform(post("/employee/login").content(requestBody)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE));

        HttpSession session = resultActions.andReturn().getRequest().getSession();
        Employee principal = (Employee) session.getAttribute("principal");
        System.out.println("테스트: " + principal.getEmployeeName());
        System.out.println("테스트: " + principal.getEmployeePassword());

        // then
        assertThat(principal.getEmployeeName()).isEqualTo("ssar");
        resultActions.andExpect(status().isOk());

    }

}
