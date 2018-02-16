package hello;

import hello.domain.StockRecordService;
import hello.model.Book;
import hello.model.StockRecord;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Status;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;
import java.util.stream.Stream;

import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest
@AutoConfigureMockMvc
public class HelloTest {

    @MockBean
    private StockRecordService stockRecordService;

    @Autowired
    private MockMvc mvc;

    @Test
    public void getHello() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/greetings/Tushar").accept(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.content().string(Matchers.anyOf(Matchers.equalToIgnoringCase(("Hello Tushar. Welcome to Advanced Technologies DevOps Stall")),Matchers.equalTo("Hello Mother"))));
    }

    @Test
    public void getStock() throws Exception {
        when(stockRecordService.retrieveAllStockInformation()).thenReturn(Stream.<StockRecord>builder().build());
        mvc.perform(MockMvcRequestBuilders.get("/stock").accept(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void getStockByAccessionNo() throws Exception {
        when(stockRecordService.retrieveByAccessionNo("384240")).thenReturn(StockRecord.create("384240", Book.create("2342342424","Sample")));
        mvc.perform(MockMvcRequestBuilders.get("/stock/384240").accept(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk());
    }
}
