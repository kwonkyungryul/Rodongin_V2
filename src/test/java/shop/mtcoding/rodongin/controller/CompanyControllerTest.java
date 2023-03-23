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
    public void detail_test() throws Exception {
        // given
        int id = 1;

        // when
        ResultActions resultActions = mvc.perform(
                get("/companies/" + id));
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
        ResultActions resultActions = mvc.perform(post("/companies/login").content(requestBody)
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

        String requestBody = om.writeValueAsString(companyUpdateInDto);
        System.out.println("테스트 : " + requestBody);

        // when
        ResultActions resultActions = mvc.perform(
                put("/companies/" + id)
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .session(mockSession));

        // then
        resultActions.andExpect(status().isOk());
        resultActions.andExpect(jsonPath("$.code").value(1));
        resultActions.andExpect(jsonPath("$.msg").value("기업소개 수정성공"));
        resultActions.andExpect(jsonPath("$.data").isEmpty());
    }


   
    
}
