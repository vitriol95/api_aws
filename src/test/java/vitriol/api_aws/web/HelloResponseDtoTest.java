package vitriol.api_aws.web;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import vitriol.api_aws.web.dto.HelloResponseDto;

import static org.assertj.core.api.Assertions.assertThat;

class HelloResponseDtoTest {

    @DisplayName("롬복 기능 테스트")
    @Test
    void lombok(){
        String name = "test";
        int amount = 1000;

        HelloResponseDto dto = new HelloResponseDto(name, amount);

        assertThat(dto.getName()).isEqualTo(name);
        assertThat(dto.getAmount()).isEqualTo(amount);
    }
}