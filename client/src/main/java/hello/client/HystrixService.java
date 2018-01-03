package hello.client;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Service
public class HystrixService {

    private final RestTemplate restTemplate;

    public HystrixService(RestTemplate rest) {
        this.restTemplate = rest;
    }
    @HystrixCommand(fallbackMethod = "reindex")
    public String index() {
        URI uri = URI.create("http://localhost:9000/greetings");
        return restTemplate.getForObject(uri,String.class);
    }

    public String reindex() {
        return "Hello Mother";
    }
}
