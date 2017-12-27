package hello.repository;

import hello.entity.StockRecordEntity;
import hello.model.StockRecord;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

@Primary
public interface StockRecordJPARepository extends JpaRepository<StockRecordEntity,String>{

    @Query("Select e from StockRecordEntity e")
    Stream<StockRecordEntity> getAsStream();

}
