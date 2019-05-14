package lu.gekod.mspoc.service_1;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("mspoc-service2")
public interface GreetingClient {

    /**
     * Request for retrieving a greeting.
     * @return the greeting from the peer service.
     */
    @RequestMapping("/greeting-service-2")
    String greeting();
}
