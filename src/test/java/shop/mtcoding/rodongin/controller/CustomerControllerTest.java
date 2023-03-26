package shop.mtcoding.rodongin.controller;

import org.junit.jupiter.api.Test;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

@Transactional // 메서드 실행 직후 롤백!! // auto_increment 초기화
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
public class CustomerControllerTest {

  @Autowired
  private MockMvc mvc;

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

  @Test
  public void list_test() throws Exception {
    // given

    // when
    ResultActions resultActions = mvc.perform(
        get("/customers/"));
    String responseBody = resultActions.andReturn().getResponse().getContentAsString();
    System.out.println("테스트 : " + responseBody);
    // then
    resultActions.andExpect(status().isOk());
    resultActions.andExpect(jsonPath("$.code").value(1));
    resultActions.andExpect(jsonPath("$.data[0].id").value(11));
    resultActions.andExpect(jsonPath("$.data[0].customerTitle").value("문의한 내용에 대한 답변이 오지 않았어요"));
    resultActions.andExpect(jsonPath("$.data[0].employee.id").value(11));
    resultActions.andExpect(jsonPath("$.data[0].employee.employeeName").value("apple"));
  }
}

// private Integer id;
// private String customerTitle;
// private EmployeeDto employee;
// private Timestamp createdAt;
