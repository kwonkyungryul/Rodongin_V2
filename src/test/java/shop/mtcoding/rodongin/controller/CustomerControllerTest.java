package shop.mtcoding.rodongin.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import shop.mtcoding.rodongin.dto.customer.CustomerSaveInDto;
import shop.mtcoding.rodongin.model.employee.Employee;

@Transactional // 메서드 실행 직후 롤백!! // auto_increment 초기화
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
public class CustomerControllerTest {

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
    CustomerSaveInDto customerSaveInDto = CustomerSaveInDto.builder()
        .customerTitle("이력서 등록이 안 돼요")
        .customerContent("이력서 등록이 안 되는데 어떻게 해야 하나요?").build();

    String requestBody = om.writeValueAsString(customerSaveInDto);
    // when

    ResultActions resultActions = mvc.perform(
        post(
            "/customers")
            .content(requestBody)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .session(mockSession));

    System.out.println("테스트 : " + resultActions);

    // then
    resultActions.andExpect(status().isCreated());
  }

  @Test
  public void detail_test() throws Exception {
    // given
    int id = 1;

    // when
    ResultActions resultActions = mvc.perform(
        get("/customers/" + id));
    String responseBody = resultActions.andReturn().getResponse().getContentAsString();
    System.out.println("테스트 : " + responseBody);
    // then

    resultActions.andExpect(jsonPath("$.code").value(1));
    resultActions.andExpect(status().isOk());
  }

}
