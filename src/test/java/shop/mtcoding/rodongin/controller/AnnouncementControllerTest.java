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

    @BeforeEach
    public void setUp() {
        // 세션 주입
        Company company = new Company();
        company.setId(1);
        company.setCompanyUsername("SAMSUNG");
        company.setCompanyPassword(
                "96e60856f35fafa9b394a64812f93659$ede46b1fc6024720c6b8690c57a9bc3d84ec705a88c5e081c7599500ebf15ce0");
        // company.setEmployeeEmail("ssar@nate.com");
        // company.setEmployeeBirth(date);
        // company.setEmployeeTel("01011111111");
        // company.setEmployeeAddress("서울특별시 강남구");

        mockSession = new MockHttpSession();
        mockSession.setAttribute("comPrincipal", company);
    }

    @Test
    public void delete_test() throws Exception {
        // given
        int id = 1;

        // when
        ResultActions resultActions = mvc.perform(
                delete("/announcements/" + id).session(mockSession));
        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println("delete_test : " + responseBody);
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
                post("/announcements")
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .session(mockSession));
        System.out.println("테스트 : " + requestBody);
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
                get("/announcements/" + id));
        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println("테스트 : " + responseBody);
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
        System.out.println("테스트 : " + requestBody);

        // when
        ResultActions resultActions = mvc.perform(
                put("/announcements/" + id)
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .session(mockSession));

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
}