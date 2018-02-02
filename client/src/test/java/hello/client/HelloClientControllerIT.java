package hello.client;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URL;
import java.util.List;
import java.util.regex.Pattern;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class HelloClientControllerIT {

    @LocalServerPort
    private int port;

    private URL base;

    @Autowired
    private TestRestTemplate template;

    @Before
    public void setup() throws Exception {
        this.base = new URL("http://localhost:"+port);
    }

    @Test
    public void getHello() throws Exception {
        String url = base.toString().concat("/to-read");
        ResponseEntity<String> response = template.getForEntity(url.toString(),String.class);
        Assert.assertThat(response.getBody(), Matchers.anyOf(Matchers.equalToIgnoringCase(("Hello Tushar. Welcome to Advanced Technologies DevOps Stall")),Matchers.equalTo("Hello Mother")));
    }
}