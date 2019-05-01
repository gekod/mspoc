package lu.gekod.mspoc.service_1;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import javax.inject.Named;

@SpringBootApplication
public class Service1Application {

    public static void main(String[] args) {
        SpringApplication.run(Service1Application.class, args);
    }
}

@RestController
class MessageRestController {

    private ServiceProperties serviceProperties;

    @Inject
    public MessageRestController(ServiceProperties serviceProperties) {
        this.serviceProperties = serviceProperties;
    }

    @GetMapping("/message")
    String getMessage() {
        return this.serviceProperties.getMessage();
    }
}

@Named
@RefreshScope
class ServiceProperties {

    @Value("${message:Hello default}")
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
