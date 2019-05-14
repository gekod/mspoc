package lu.gekod.mspoc.service_1;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
class Service1Controller {

    private Service1Properties serviceProperties;

    private GreetingClient greetingClient;

    @Inject
    public Service1Controller(Service1Properties serviceProperties, GreetingClient greetingClient) {
        this.serviceProperties = serviceProperties;
        this.greetingClient = greetingClient;
    }

    @GetMapping("/message")
    String getMessage() {
        return this.serviceProperties.getMessage();
    }

    @GetMapping("/greeting")
    @HystrixCommand(
            commandKey = "greetingFromService2",
            fallbackMethod = "fallbackGreeting",
            ignoreExceptions = {})
    public String greeting() {
        return greetingClient.greeting();
    }

    private String fallbackGreeting() {
        return "Default Greeting";
    }
}
