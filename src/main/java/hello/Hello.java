package hello;

import com.google.common.collect.ImmutableList;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import hello.domain.StockRecordService;
import hello.model.StockRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/")
public class Hello {

    @Autowired
    StockRecordService stockRecordService;

    @GetMapping("greetings/{name}")
    public String index(@PathVariable String name) {
        return "Hello "+name +". Welcome to Advanced Technologies DevOps Stall";
    }

    @GetMapping("stock")
    @Transactional(readOnly = true)
    public List<StockRecord> retrieveAllStockInformation() {
        return stockRecordService.retrieveAllStockInformation().collect(Collectors.toList());
    }

    @PostMapping("stock/register")
    public StockRecord register(@RequestBody StockRecordInfo stockRecordInfo) {
        return stockRecordService.register(stockRecordInfo.getAccessionNo(),stockRecordInfo.getIsbn(),stockRecordInfo.getTitle());
    }

    @DeleteMapping("stock/deregister/{accessionNo}")
    public boolean deRegisterStockWith(@PathVariable String accessionNo) {
        return stockRecordService.remove(accessionNo);
    }

    @GetMapping("stock/{accessionNo}")
    public StockRecord retrieveBy(@PathVariable String accessionNo) {
        return stockRecordService.retrieveByAccessionNo(accessionNo);
    }

/*    @Transactional(readOnly = true)
    @GetMapping("stock/stream/list/all")
    public List<StockRecord> retrieveAllStockInformationUsingStreamList() {
        List<StockRecord> stockRecords = Collections.emptyList();

        try(Stream<StockRecordEntity> stream = stockRecordRepository.getAsStream()) {
            stockRecords = stream.map(e -> StockRecord.create(e.getAccessionNo(), Book.create(e.getIsbn(),"sample"))).collect(Collectors.toList());
        }
        return stockRecords;
    }*/



/*    @Transactional(readOnly = true)
    @GetMapping("stock/stream/future/all")
    public List<StockRecord> retrieveAllStockInformationUsingStreamFuture() {
        StockRecordJPARepository lRepository = stockRecordRepository;
        return lRepository.getAsStream().map(e -> CompletableFuture.supplyAsync(() -> (StockRecord.create(e.getAccessionNo(),Book.create(e.getIsbn(),"sample")))).thenApply(p -> Collectors.toList()));
    }

    @Transactional(readOnly = true)
    @GetMapping("stock/stream/futurex/all")
    public CompletableFuture<List<StockRecord>> retrieveAllStockInformationUsingStreamFutureX() {
        StockRecordJPARepository lRepository = stockRecordRepository;
        //List<StockRecord> records;
        return CompletableFuture.supplyAsync(() -> lRepository.getAsStream().map(e -> (StockRecord.create(e.getAccessionNo(),Book.create(e.getIsbn(),"sample")))).collect(Collectors.toList())).thenApply(e -> e);
        //return records;
    }*/


/*    @Transactional(readOnly = true)
    @GetMapping("stock/stream/all")
    public Stream<StockRecord> retrieveAllStockInformationUsingStream() {

        return stockRecordRepository.getAsStream().map(e -> (StockRecord.create(e.getAccessionNo(),Book.create(e.getIsbn(),"sample"))));
        //return stockRecords;
    }*/
}
