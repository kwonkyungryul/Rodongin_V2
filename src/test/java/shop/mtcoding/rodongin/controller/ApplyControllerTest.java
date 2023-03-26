package shop.mtcoding.rodongin.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import shop.mtcoding.rodongin.model.company.Company;

@Transactional
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
public class ApplyControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper om;

    private MockHttpSession mockSession;

    @BeforeEach
    public void setUp() {
        // 세션 주입
        Company company = new Company();
        company.setId(1);
        company.setCompanyUsername("SAMSUNG");
        company.setCompanyPassword(
                "96e60856f35fafa9b394a64812f93659$ede46b1fc6024720c6b8690c57a9bc3d84ec705a88c5e081c7599500ebf15ce0");

        mockSession = new MockHttpSession();
        mockSession.setAttribute("comPrincipal", company);
    }

    @Test
    public void applyList_test() throws Exception {
        // given
        int announcementId = 1;
        // when
        ResultActions resultActions = mvc.perform(
                get("/apply/" + announcementId));
        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println(responseBody);
        // then

        resultActions.andExpect(status().isOk());
        resultActions.andExpect(jsonPath("$.code").value(1));
        resultActions.andExpect(jsonPath("$.data[0].id").value(1));
        resultActions.andExpect(jsonPath("$.data[0].resumeDto.resumeTitle").value("개발자 이력서"));
        resultActions.andExpect(jsonPath("$.data[0].employeeDto.employeeFullname").value("홍길동"));
        resultActions.andExpect(jsonPath("$.data[0].resumeStackDto[0].stackMasterDto.stackName").value("Java"));
        resultActions.andExpect(jsonPath("$.data[0].resumeCareerDto[0].careerCompany").value("ABC IT Solutions"));
    }
}
