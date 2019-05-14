package lu.gekod.mspoc.service_1;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import javax.inject.Named;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@EnableCircuitBreaker
@EnableHystrix
public class Service1Application {

    public static void main(String[] args) {
        SpringApplication.run(Service1Application.class, args);
    }
}


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


@Named
@RefreshScope
class Service1Properties {

    @Value("${message:Hello default}")
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
