package lu.gekod.mspoc.configuration_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class ConfigurationServerApplication {
     
    public static void main(String[] arguments) {
        SpringApplication.run(ConfigurationServerApplication.class, arguments);
    }
}
