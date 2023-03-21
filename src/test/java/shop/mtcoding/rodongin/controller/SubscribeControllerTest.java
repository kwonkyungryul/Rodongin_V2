package shop.mtcoding.rodongin.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

import shop.mtcoding.rodongin.model.employee.Employee;

@Transactional // 메서드 실행 직후 롤백!! // auto_increment 초기화
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
public class SubscribeControllerTest {
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
    public void save_test() throws Exception {
        // given
        Integer companyId = 1;
        String responseBody = om.writeValueAsString(companyId);

        // when
        ResultActions resultActions = mvc.perform(post("/subscribe")
        .content(responseBody).contentType(MediaType.APPLICATION_JSON).session(mockSession));

        // then
        resultActions.andExpect(status().isCreated());
    }

    @Test
    public void delete_test() throws Exception {
        // given
        Integer companyId = 1;
        String responseBody = om.writeValueAsString(companyId);

        // when
        ResultActions resultActions = mvc.perform(delete("/subscribe")
        .content(responseBody).contentType(MediaType.APPLICATION_JSON).session(mockSession));

        // then
        resultActions.andExpect(status().isOk());
    }
}
