package info.mayankag.springbootconfigclient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @Value("${status}")
    private String status;

    @Value("${secureStringstatus}")
    private String secureStringsStatus;

    @GetMapping("/status")
    public String Status() {
        return status + " : " + secureStringsStatus;
    }
}
