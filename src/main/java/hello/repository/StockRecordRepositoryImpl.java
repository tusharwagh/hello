package hello.repository;

import hello.domain.StockRecordRepository;
import hello.entity.StockRecordEntity;
import hello.model.Book;
import hello.model.StockRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.function.Function;
import java.util.stream.Stream;

@Repository
public class StockRecordRepositoryImpl implements StockRecordRepository {

    @Autowired
    StockRecordJPARepository jpaRepository;

    @Autowired
    StockRecordJDBCRepository jdbcRepository;

/*    public StockRecordRepositoryImpl(StockRecordJDBCRepository jdbcRepository) {
        this.jdbcRepository = jdbcRepository;
    }*/

    private static StockRecord map(StockRecordEntity stockRecordEntity) {
        return StockRecord.create(stockRecordEntity.getAccessionNo(),Book.create(stockRecordEntity.getIsbn(),"sample"),stockRecordEntity.getStatus());
    }

    private static StockRecordEntity map(StockRecord stockRecord) {
        return new StockRecordEntity(stockRecord);
    }

    @Override
    public Stream<StockRecord> retrieveAllStockInformation() {
        Function<StockRecordEntity,StockRecord> mapper = StockRecordRepositoryImpl::map;
        return jpaRepository.getAsStream().map(e -> mapper.apply(e));
        //return jdbcRepository.retrieveStockInformation().map(e -> mapper.apply(e));
    }

    @Override
    public StockRecord retrieveByAccessionNo(String accessionNo) {
        Function<StockRecordEntity,StockRecord> mapper = StockRecordRepositoryImpl::map;
        return mapper.apply(jpaRepository.findOne(accessionNo));
    }

    @Override
    public boolean removeByAccessionNo(String accessionNo) {
        jpaRepository.delete(accessionNo);
        return true;
    }

    @Override
    public StockRecord register(StockRecord stockRecord) {
        Function<StockRecord,StockRecordEntity> mapper = StockRecordRepositoryImpl::map;
        Function<StockRecordEntity,StockRecord> reverseMapper = StockRecordRepositoryImpl::map;
        return reverseMapper.apply(jpaRepository.save(mapper.apply(stockRecord)));
    }


    public Stream<StockRecord> retrieveAllStockWithJDBCTemplate() {
        Function<StockRecordEntity,StockRecord> mapper = StockRecordRepositoryImpl::map;
        return jpaRepository.getAsStream().map(e -> mapper.apply(e));
    }
}
