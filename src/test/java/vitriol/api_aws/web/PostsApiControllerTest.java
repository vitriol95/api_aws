package vitriol.api_aws.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import vitriol.api_aws.domain.post.Posts;
import vitriol.api_aws.domain.post.PostsRepository;
import vitriol.api_aws.web.dto.PostsSaveRequestDto;
import vitriol.api_aws.web.dto.PostsUpdateRequestDto;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PostsApiControllerTest {

    @LocalServerPort
    int port;

    @Autowired
    TestRestTemplate testRestTemplate;

    @Autowired
    PostsRepository postsRepository;

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @BeforeEach
    void beforeEach(){
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @AfterEach
    void afterEach() {
        postsRepository.deleteAll();
    }

    @DisplayName("post 등록")
    @WithMockUser(roles = "USER")
    @Test
    void post2() throws Exception {
        String title = "title";
        String content = "content";
        PostsSaveRequestDto requestDto = PostsSaveRequestDto.builder()
                .title(title)
                .content(content)
                .author("vitriol")
                .build();
        String url = "http://localhost:" + port + "/api/v1/posts";
//        ResponseEntity<Long> responseEntity = testRestTemplate.postForEntity(url, requestDto, Long.class);
//        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
//        assertThat(responseEntity.getBody()).isGreaterThan(0L);
//
//        List<Posts> all = postsRepository.findAll();
//        assertThat(all.get(0).getTitle()).isEqualTo(title);
//        assertThat(all.get(0).getContent()).isEqualTo(content);
//        assertThat(all.get(0).getAuthor()).isEqualTo("vitriol");
        mvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(requestDto)))
                .andExpect(status().isOk());

        List<Posts> all = postsRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(title);
        assertThat(all.get(0).getContent()).isEqualTo(content);

    }

    @DisplayName("post 수정")
    @WithMockUser(roles = "USER")
    @Test
    void update() throws Exception {
        Posts save = postsRepository.save(Posts.builder()
                .title("title")
                .content("content")
                .author("vitriol")
                .build());

        Long id = save.getId();
        PostsUpdateRequestDto updateRequestDto = PostsUpdateRequestDto.builder()
                .title("title2")
                .content("content2")
                .build();

        String url = "http://localhost:" + port + "/api/v1/posts/" + id;
//        HttpEntity<PostsUpdateRequestDto> requestEntity = new HttpEntity<>(updateRequestDto);
//        ResponseEntity<Long> responseEntity = testRestTemplate.exchange(url, HttpMethod.PUT, requestEntity, Long.class);
//
//        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
//        assertThat(responseEntity.getBody()).isGreaterThan(0L);
//
//        List<Posts> all = postsRepository.findAll();
//        assertThat(all.get(0).getId()).isEqualTo(id);
//        assertThat(all.get(0).getTitle()).isEqualTo("title2");
//        assertThat(all.get(0).getContent()).isEqualTo("content2");
        mvc.perform(put(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(updateRequestDto)))
                .andExpect(status().isOk());

        List<Posts> all = postsRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo("title2");
        assertThat(all.get(0).getContent()).isEqualTo("content2");

    }
}