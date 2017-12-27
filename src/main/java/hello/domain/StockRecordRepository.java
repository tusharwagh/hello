package hello.domain;

import hello.model.StockRecord;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.stream.Stream;

public interface StockRecordRepository {

    public Stream<StockRecord> retrieveAllStockInformation();

    public StockRecord retrieveByAccessionNo(String accessionNo);

    public boolean removeByAccessionNo(String accessionNo);

    public StockRecord register(StockRecord stockRecord);
}
