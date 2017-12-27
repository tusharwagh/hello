package hello;

import com.lms.common.swagger.CommonSwaggerConfiguration;
import hello.domain.StockRecordRepository;
import hello.domain.StockRecordService;
import hello.repository.StockRecordJPARepository;
import hello.repository.StockRecordRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

import javax.sql.DataSource;
import java.util.Arrays;

@SpringBootApplication
@Import(CommonSwaggerConfiguration.class)
public class Application {

    public static void main (String args[]) {
        SpringApplication.run(Application.class,args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {

            System.out.println("Let's inspect the beans provided by Spring Boot:");
            String[] beanNames = ctx.getBeanDefinitionNames();
            Arrays.sort(beanNames);
            for (String beanName : beanNames) {
                System.out.println(beanName);
            }

        };
    }

/*    @Bean
    public StockRecordRepository getStockRecordRepository() {
        return new StockRecordRepositoryImpl();
    }

    @Bean
    public StockRecordService getStockRecordService() {
        return new StockRecordService(getStockRecordRepository());
    }

    @Bean
    public StockRecordJPARepository getStockRecordJPARepository(){
        return null;
    }*/
}
