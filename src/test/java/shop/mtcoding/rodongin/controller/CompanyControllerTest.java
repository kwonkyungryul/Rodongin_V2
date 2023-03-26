package shop.mtcoding.rodongin.controller;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigInteger;
import java.sql.Date;

import javax.servlet.http.HttpSession;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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
import shop.mtcoding.rodongin.dto.company.CompanyJoinInDto;
import shop.mtcoding.rodongin.dto.company.CompanyLoginInDto;
import shop.mtcoding.rodongin.dto.company.CompanyUpdateInDto;
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

    String jwt = JwtProvider.create(Company.builder()
            .id(1)
            .companyRole("company")
            .build());
    
    @Test
    public void detail_test() throws Exception {
        // given
        int id = 1;

        // when
        ResultActions resultActions = mvc.perform(
                get("/companies/" + id));
        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
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
        // when
        ResultActions resultActions = mvc.perform(post("/companies/login").content(requestBody)
                .contentType(MediaType.APPLICATION_JSON_VALUE));

//        HttpSession session = resultActions.andReturn().getRequest().getSession();
//        Company principal = (Company) session.getAttribute("comPrincipal");

        // then
//        assertThat(principal.getCompanyUsername()).isEqualTo("NAVER");
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
        ResultActions resultActions = mvc.perform(post("/companies/join").content(requestBody)
                .contentType(MediaType.APPLICATION_JSON_VALUE));
    
        // then
        resultActions.andExpect(status().isCreated());
        resultActions.andExpect(jsonPath("$.code").value(1));
        resultActions.andExpect(jsonPath("$.msg").value("기업회원가입완료"));
        resultActions.andExpect(jsonPath("$.data").isEmpty());
    }
    
    @Test
    public void update_test() throws Exception {
        // given

        
        int id = 1;
        Date date = new Date(2022 - 01 - 12);
        CompanyUpdateInDto companyUpdateInDto = new CompanyUpdateInDto();
        companyUpdateInDto.setCompanyFullname("KKK");
        companyUpdateInDto.setCompanyThumbnail("coke");
        companyUpdateInDto.setCompanyEstablish(date);
        companyUpdateInDto.setCompanySales(new BigInteger("1234"));
        companyUpdateInDto.setCompanyEmployeesNumber(1);
        companyUpdateInDto.setCompanyIntroduction("1234");
        companyUpdateInDto.setCompanyHistory("asd");
        companyUpdateInDto.setCompanyVision("sadasd");
        companyUpdateInDto.setCompanyThumbnail("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");

        String requestBody = om.writeValueAsString(companyUpdateInDto);

        // when
        ResultActions resultActions = mvc.perform(
                put("/s/companies/" + id)
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .header("Authorization", jwt));

        // then
        resultActions.andExpect(status().isOk());
        resultActions.andExpect(jsonPath("$.code").value(1));
        resultActions.andExpect(jsonPath("$.msg").value("기업소개 수정성공"));
        resultActions.andExpect(jsonPath("$.data").isEmpty());
    }


   
    
}
