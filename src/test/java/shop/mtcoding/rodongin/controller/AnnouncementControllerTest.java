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
import shop.mtcoding.rodongin.dto.announcement.AnnouncementSaveInDto;
import shop.mtcoding.rodongin.dto.announcement.AnnouncementUpdateInDto;
import shop.mtcoding.rodongin.model.company.Company;

@Transactional // 메서드 실행 직후 롤백!! // auto_increment 초기화
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
public class AnnouncementControllerTest {

    @Autowired
    private MockMvc mvc;
    private MockHttpSession mockSession;

    @Autowired
    private ObjectMapper om;

    String jwt = JwtProvider.create(Company.builder()
            .id(1)
            .companyRole("company")
            .build());

    @Test
    public void delete_test() throws Exception {
        // given
        int id = 1;

        // when
        ResultActions resultActions = mvc.perform(
                delete("/s/announcements/" + id).header("Authorization", jwt));
        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        resultActions.andExpect(jsonPath("$.code").value(1));
        resultActions.andExpect(status().isOk());
    }

    @Test
    public void save_test() throws Exception {
        // given
        AnnouncementSaveInDto announcementSaveInDto = new AnnouncementSaveInDto();
        announcementSaveInDto.setStackId(1);
        announcementSaveInDto.setAnnouncementTitle("asdfsdf");
        announcementSaveInDto.setAnnouncementContent("asdfasdf");
        announcementSaveInDto.setAnnouncementCarrer("신입");
        announcementSaveInDto.setAnnouncementHireType("정규직");
        announcementSaveInDto.setAnnouncementRecNum(1);
        announcementSaveInDto.setAnnouncementSalary("3500");
        announcementSaveInDto.setAnnouncementArea("스울강역시");

        String requestBody = om.writeValueAsString(announcementSaveInDto);
        // when
        ResultActions resultActions = mvc.perform(
                post("/s/announcements")
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
                get("/announcements/" + id));
        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        // then

        resultActions.andExpect(jsonPath("$.code").value(1));
        resultActions.andExpect(status().isOk());
    }

    @Test
    public void update_test() throws Exception {
        // given
        int id = 1;

        AnnouncementUpdateInDto announcementUpdateInDto = AnnouncementUpdateInDto.builder()
                .stackId(1)
                .announcementTitle("asdf---df")
                .announcementContent("asd---fasdf")
                .announcementCarrer("신입")
                .announcementHireType("정규직")
                .announcementRecNum(1)
                .announcementSalary("3500")
                .announcementArea("스울강역시")
                .build();

        String requestBody = om.writeValueAsString(announcementUpdateInDto);

        // when
        ResultActions resultActions = mvc.perform(
                put("/s/announcements/" + id)
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .header("Authorization", jwt));

        // then
        resultActions.andExpect(status().isOk());
        resultActions.andExpect(jsonPath("$.code").value(1));
    }

    @Test
    public void list_test() throws Exception {
        // given

        // when
        ResultActions resultActions = mvc.perform(
                get("/"));
        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println(responseBody);
        // then

        resultActions.andExpect(status().isOk());
        resultActions.andExpect(jsonPath("$.code").value(1));
        resultActions.andExpect(jsonPath("$.data.content").value(""));
        resultActions.andExpect(jsonPath("$.data.announcementListDto[0].id").value(66));
    }

    @Test
    public void comList_test() throws Exception {
        // given
        int companyId = 1;
        // when
        ResultActions resultActions = mvc.perform(
                get("/s/announcements/companies/" + companyId).header("Authorization", jwt));
        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println(responseBody);
        // then

        resultActions.andExpect(status().isOk());
        resultActions.andExpect(jsonPath("$.code").value(1));
        resultActions.andExpect(jsonPath("$.data[0].stack.stackName").value("Java"));
    }
}