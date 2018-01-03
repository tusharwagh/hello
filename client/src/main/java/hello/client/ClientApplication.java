package hello.client;

import com.lms.common.swagger.CommonSwaggerConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(CommonSwaggerConfiguration.class)
public class ClientApplication {

    public static void main (String args[]) {
        SpringApplication.run(ClientApplication.class,args);
    }
}
