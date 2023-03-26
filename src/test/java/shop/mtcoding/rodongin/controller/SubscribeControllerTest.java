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

import shop.mtcoding.rodongin.config.auth.JwtProvider;
import shop.mtcoding.rodongin.dto.subscribe.SubscribeSaveInDto;
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

    String jwt = JwtProvider.create(Employee.builder()
            .id(1)
            .employeeRole("employee")
            .build());

    @Test
    public void save_test() throws Exception {
        // given
        SubscribeSaveInDto subscribeSaveInDto = SubscribeSaveInDto.builder()
                .announcementId(1).build();

        String responseBody = om.writeValueAsString(subscribeSaveInDto);

        // when
        ResultActions resultActions = mvc.perform(post("/s/subscribes")
                .content(responseBody).contentType(MediaType.APPLICATION_JSON).header("Authorization", jwt));

        // then
        resultActions.andExpect(status().isCreated());
    }

    @Test
    public void delete_test() throws Exception {
        // given
        Integer announcementId = 1;

        // when
        ResultActions resultActions = mvc.perform(delete("/s/subscribes/" + announcementId)
                .header("Authorization", jwt));

        // then
        resultActions.andExpect(status().isOk());
    }
}
