package vitriol.api_aws.domain.post;

import org.aspectj.lang.annotation.After;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @AfterEach
    void afterEach() {
        postsRepository.deleteAll();
    }

    @DisplayName("게시글 저장 및 불러오기")
    @Test
    void post1() {
        String title = "게시글 제목";
        String content = "게시글 본문";

        postsRepository.save(Posts.builder().title(title).content(content).author("vitriol").build());
        List<Posts> all = postsRepository.findAll();
        Posts posts = all.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
        assertThat(posts.getAuthor()).isEqualTo("vitriol");
    }

    @DisplayName("BaseTimeEntity 적용 테스트")
    @Test
    void time() {

        LocalDateTime before = LocalDateTime.of(2021, 5, 5, 0, 0, 0);
        postsRepository.save(Posts.builder().title("title").content("content").author("vitriol").build());
        List<Posts> all = postsRepository.findAll();
        Posts posts = all.get(0);
        System.out.println(posts.getCreatedDate());
        System.out.println(posts.getModifiedDate());
        assertThat(posts.getCreatedDate()).isAfter(before);
        assertThat(posts.getModifiedDate()).isAfter(before);
    }
}