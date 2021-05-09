package vitriol.api_aws.web;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class IndexControllerTest {

    @Autowired
    TestRestTemplate restTemplate;

    @DisplayName("메인 페이지")
    @Test
    void main(){
        String body = this.restTemplate.getForObject("/", String.class);
        assertThat(body).contains("Spring Boot with Aws");
    }

}