package vitriol.api_aws.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vitriol.api_aws.web.dto.HelloResponseDto;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/hello/dto")
    public HelloResponseDto helloResponseDto(@RequestParam String name, @RequestParam int amount) {
        return new HelloResponseDto(name, amount);
    }
}
