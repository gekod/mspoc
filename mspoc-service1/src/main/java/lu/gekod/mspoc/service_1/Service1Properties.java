package lu.gekod.mspoc.service_1;

import javax.inject.Named;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;

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
