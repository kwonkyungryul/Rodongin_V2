package shop.mtcoding.rodongin.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
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

import shop.mtcoding.rodongin.config.auth.JwtProvider;
import shop.mtcoding.rodongin.dto.customer.CustomerSaveInDto;
import shop.mtcoding.rodongin.dto.customer.CustomerUpdateInDto;
import shop.mtcoding.rodongin.model.company.Company;
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

  String jwt = JwtProvider.create(Employee.builder()
          .id(1)
          .employeeRole("employee")
          .build());

  @Test
  public void update_test() throws Exception {
    // given
    int id = 1;

    CustomerUpdateInDto customerUpdateInDto = CustomerUpdateInDto.builder()
        .customerTitle("이력서 등록이 안 돼요")
        .customerContent("이력서 등록이 안 되는데 어떻게 해야 하나요?").build();

    String requestBody = om.writeValueAsString(customerUpdateInDto);

    // when
    ResultActions resultActions = mvc.perform(
        put("/s/customers/" + id)
            .content(requestBody)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .header("Authorization", jwt));

    // then
    resultActions.andExpect(status().isOk());
    resultActions.andExpect(jsonPath("$.code").value(1));
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
            "/s/customers")
            .content(requestBody)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .header("Authorization", jwt));

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
    // then

    resultActions.andExpect(jsonPath("$.code").value(1));
    resultActions.andExpect(status().isOk());
  }

  @Test
  public void list_test() throws Exception {
    // given

    // when
    ResultActions resultActions = mvc.perform(
        get("/customers/"));
    String responseBody = resultActions.andReturn().getResponse().getContentAsString();
    // then
    resultActions.andExpect(status().isOk());
    resultActions.andExpect(jsonPath("$.code").value(1));
    resultActions.andExpect(jsonPath("$.data[0].id").value(11));
    resultActions.andExpect(jsonPath("$.data[0].customerTitle").value("문의한 내용에 대한 답변이 오지 않았어요"));
    resultActions.andExpect(jsonPath("$.data[0].employee.id").value(11));
    resultActions.andExpect(jsonPath("$.data[0].employee.employeeName").value("apple"));
  }

  @Test
  public void delete_test() throws Exception {
    // given
    int id = 1;

    // when
    ResultActions resultActions = mvc.perform(
        delete("/s/customers/" + id).header("Authorization", jwt));
    String responseBody = resultActions.andReturn().getResponse().getContentAsString();
    // then
    resultActions.andExpect(jsonPath("$.code").value(1));
    resultActions.andExpect(status().isOk());
  }

}
