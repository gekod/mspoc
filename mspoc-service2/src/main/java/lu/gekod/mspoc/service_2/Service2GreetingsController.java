package lu.gekod.mspoc.service_2;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Service2GreetingsController {

    @GetMapping("/greeting-service-2")
    public String greeting() {
        return "Greeting from service 2";
    }
}
