package vitriol.api_aws.web;

import org.junit.jupiter.api.Test;
import org.springframework.mock.env.MockEnvironment;
import static org.assertj.core.api.Assertions.assertThat;

class ProfileControllerTest {

    @Test
    void real_profile_조회() {
        String expectedProfile = "real";
        MockEnvironment env = new MockEnvironment();
        env.addActiveProfile(expectedProfile);
        env.addActiveProfile("oauth");
        env.addActiveProfile("real-db");

        ProfileController controller = new ProfileController(env);

        String profile = controller.profile();
        assertThat(profile).isEqualTo(expectedProfile);
    }

    @Test
    void active_profile이_없다면_default가_조회() {
        String expectedProfile = "default";
        MockEnvironment env = new MockEnvironment();
        ProfileController controller = new ProfileController(env);

        String profile = controller.profile();
        assertThat(profile).isEqualTo(expectedProfile);
    }
}