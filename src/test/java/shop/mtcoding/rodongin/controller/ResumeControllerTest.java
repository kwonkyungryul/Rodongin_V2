package shop.mtcoding.rodongin.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.rodongin.config.auth.JwtProvider;
import shop.mtcoding.rodongin.dto.announcement.AnnouncementSaveInDto;
import shop.mtcoding.rodongin.dto.resume.ResumeSaveInDto;
import shop.mtcoding.rodongin.dto.resume.ResumeUpdateInDto;
import shop.mtcoding.rodongin.model.employee.Employee;
import shop.mtcoding.rodongin.model.resume.Resume;
import shop.mtcoding.rodongin.model.resume.ResumeRepository;

import java.sql.Date;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@Transactional
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class ResumeControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper om;

    private MockHttpSession mockSession;

    @Autowired
    private ResumeRepository resumeRepository;

    String jwt = JwtProvider.create(Employee.builder()
            .id(1)
            .employeeRole("employee")
            .build());

    @Test
    public void detail_test() throws Exception {
        // given
        int id = 1;
        // when
        ResultActions resultActions = mvc.perform(
                get("/resumes/" + id));
        String responseBody = resultActions.andReturn().getResponse().getContentAsString();

        // then
        resultActions.andExpect(status().isOk());
        resultActions.andExpect(jsonPath("$.code").value(1));
        resultActions.andExpect(jsonPath("$.data.resumeTitle").value("개발자 이력서"));
    }

    @Test
    public void save_test() throws Exception {
        // given
        ResumeSaveInDto resumeSaveInDto = ResumeSaveInDto.builder()
                .id(26)
                .resumeTitle("이력서제목")
                .schoolId(1)
                .schoolGraduate("졸업")
                .careerCompany("한샘")
                .careerStart(new Date(1990 - 01 - 01))
                .careerEnd(new Date(2022 - 03 - 23))
                .licenseId(1)
                .licenseIssuer("발행처")
                .stackId(1)
                .stackAcquisition("활용도")
                .resumeSalary("2000")
                .CV("자기소개서").build();

        // when
        String requestBody = om.writeValueAsString(resumeSaveInDto);
        ResultActions resultActions = mvc.perform(
                post("/s/resumes")
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .header("Authorization", jwt));

        // then
        resultActions.andExpect(status().isCreated())
                .andExpect(jsonPath("$.code").value(1));
    }

    @Test
    public void update_test() throws Exception {
        // given
        int id = 1;

        ResumeUpdateInDto resumeUpdateInDto = ResumeUpdateInDto.builder()
                .resumeTitle("이력서제목")
                .schoolId(1)
                .schoolGraduate("졸업")
                .careerCompany("한샘")
                .careerStart(new Date(1990 - 01 - 01))
                .careerEnd(new Date(2022 - 03 - 23))
                .licenseId(1)
                .licenseIssuer("발행처")
                .stackId(1)
                .stackAcquisition("활용도")
                .resumeSalary("2000")
                .CV("자기소개서").build();

        // when
        String requestBody = om.writeValueAsString(resumeUpdateInDto);
        ResultActions resultActions = mvc.perform(
                put("/s/resumes/" + id)
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .header("Authorization", jwt));

        // then
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(1));
    }

    @Test
    public void delete_test() throws Exception {
        // given
        int id = 1;

        // when
        ResultActions resultActions = mvc.perform(
                delete("/s/resumes/" + id).header("Authorization", jwt));
        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println("테스트 : " + responseBody);

        resultActions.andExpect(jsonPath("$.code").value(1));
        resultActions.andExpect(status().isOk());
    }
}
