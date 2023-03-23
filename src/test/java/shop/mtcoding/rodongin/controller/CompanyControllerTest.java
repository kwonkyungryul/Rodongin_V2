package shop.mtcoding.rodongin.controller;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.servlet.http.HttpSession;

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

import shop.mtcoding.rodongin.dto.company.CompanyJoinInDto;
import shop.mtcoding.rodongin.dto.company.CompanyLoginInDto;
import shop.mtcoding.rodongin.model.company.Company;

@Transactional
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
public class CompanyControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper om;

    private MockHttpSession mockSession;
    @Test
    public void detail_test() throws Exception {
        // given
        int id = 1;

        // when
        ResultActions resultActions = mvc.perform(
                get("/company/" + id));
        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println("테스트 : " + responseBody);
        // then

        resultActions.andExpect(jsonPath("$.code").value(1));
        resultActions.andExpect(status().isOk());
    }

    @Test
    public void login_test() throws Exception {
        // given
        CompanyLoginInDto companyLoginInDto = new CompanyLoginInDto();
        companyLoginInDto.setCompanyUsername("NAVER");
        companyLoginInDto.setCompanyPassword("1234");
        String requestBody = om.writeValueAsString(companyLoginInDto);

        System.out.println("테스트 : " + requestBody);

        // when
        ResultActions resultActions = mvc.perform(post("/company/login").content(requestBody)
                .contentType(MediaType.APPLICATION_JSON_VALUE));

        HttpSession session = resultActions.andReturn().getRequest().getSession();
        Company principal = (Company) session.getAttribute("comPrincipal");

        // then
        assertThat(principal.getCompanyUsername()).isEqualTo("NAVER");
        resultActions.andExpect(status().isOk());

    }


    @Test
    public void join_test() throws Exception {
        // given
        CompanyJoinInDto companyJoinInDto = new CompanyJoinInDto();
        companyJoinInDto.setCompanyUsername("OOIUS");
        companyJoinInDto.setCompanyPassword("1234");
        companyJoinInDto.setCompanyTel("01011111111");
        companyJoinInDto.setCompanyLicenseNumber("1233");
        companyJoinInDto.setCompanyFullname("JDSOJJ");
        companyJoinInDto.setCompanyEmail("a0211a@naver.com");
        companyJoinInDto.setCompanyCeoName("M");
        companyJoinInDto.setCompanyAddress("서울특별시 강남구");
        String requestBody = om.writeValueAsString(companyJoinInDto);
    
        // when
        ResultActions resultActions = mvc.perform(post("/company/join").content(requestBody)
                .contentType(MediaType.APPLICATION_JSON_VALUE));
    
        // then
        resultActions.andExpect(status().isCreated());
        resultActions.andExpect(jsonPath("$.code").value(1));
        resultActions.andExpect(jsonPath("$.msg").value("기업회원가입완료"));
        resultActions.andExpect(jsonPath("$.data").isEmpty());
    }
    


   
    
}
