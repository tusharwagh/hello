package hello;

import hello.domain.StockRecordRepository;
import hello.domain.StockRecordService;
import hello.repository.StockRecordJDBCRepository;
import hello.repository.StockRecordJPARepository;
import hello.repository.StockRecordRepositoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@org.springframework.context.annotation.Configuration

@EnableJpaRepositories("hello.repository")
public class Configuration {

/*    public StockRecordJDBCRepository getstockJdbcRepository() {
        return new StockRecordJDBCRepository();
    }*/
/*    @Bean
    public StockRecordRepository getStockRecordRepository() {
        return new StockRecordRepositoryImpl();
    }*/

/*    @Bean
    public StockRecordService getStockRecordService() {
        return new StockRecordService(getStockRecordRepository());
    }*/
}
