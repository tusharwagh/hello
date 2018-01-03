package hello.domain;

import com.lms.common.annotation.TrackTime;
import hello.model.Book;
import hello.model.StockRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
public class StockRecordService {

    StockRecordRepository repository;

    public StockRecordService(StockRecordRepository repository) {
        this.repository = repository;
    }

    @TrackTime
    public Stream<StockRecord> retrieveAllStockInformation() {
        return repository.retrieveAllStockInformation();
    }

    @TrackTime
    public StockRecord retrieveByAccessionNo(String accessionNo) {
        return repository.retrieveByAccessionNo(accessionNo);
    }

    public StockRecord register(String accessionNo, String isbn, String title) {
        return repository.register(StockRecord.create(accessionNo, Book.create(isbn, title)));
    }

    public boolean remove(String accessionNo) {
        return repository.removeByAccessionNo(accessionNo);
    }

}
