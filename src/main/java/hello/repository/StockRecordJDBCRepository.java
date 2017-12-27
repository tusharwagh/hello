package hello.repository;

import hello.entity.StockRecordEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlRowSetResultSetExtractor;
import org.springframework.jdbc.support.rowset.ResultSetWrappingSqlRowSet;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import java.io.Closeable;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Repository
public class StockRecordJDBCRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private static StockRecordEntity map(SqlRowSet sqlRowSet) {
        String accessionNo=sqlRowSet.getString("ACCESSIONNO");
        String isbn = sqlRowSet.getString("ISBN");
        String status = sqlRowSet.getString("STATUS");
        return new StockRecordEntity(accessionNo,isbn,status);
    }

/*    public Stream<StockRecordEntity> retrieveStockInformation() {
        String sql = "Select c.* from StockRecord c";
        //jdbcTemplate.query(sql,)
        return streamQuery(sql, new Function<Stream<SqlRowSet>, Stream<StockRecordEntity>>() {
            @Override
            public Stream<StockRecordEntity> apply(Stream<SqlRowSet> sqlRowSetStream) {
                return sqlRowSetStream.map(sqlRowSet -> map(sqlRowSet));
            }
        });
    }*/

/*    private <T> T streamQueryMine(String sql) {
        return jdbcTemplate.query(sql, rs -> {
            if(rs.next()) {
                rs.get
            }
            return null;
        });
    }*/

    public class StreamableQuery implements Closeable {

        private ResultSet resultSet;

        public StreamableQuery(ResultSet resultSet) {
            this.resultSet = resultSet;
        }

        private <T> T streamQuery(String sql, Function<Stream<SqlRowSet>, ? extends T> streamer, Object... args) {
            return jdbcTemplate.query(sql, resultSet -> {
                final SqlRowSet rowSet = new ResultSetWrappingSqlRowSet(resultSet);

                Supplier<Spliterator<SqlRowSet>> supplier = () -> Spliterators.spliteratorUnknownSize(new Iterator<SqlRowSet>() {
                    @Override
                    public boolean hasNext() {
                        return !rowSet.isLast();
                    }

                    @Override
                    public SqlRowSet next() {
                        if (!rowSet.next()) {
                            throw new NoSuchElementException();
                        }
                        return rowSet;
                    }
                }, Spliterator.IMMUTABLE);
                return streamer.apply(StreamSupport.stream(supplier, Spliterator.IMMUTABLE, false));

            }, args);
        }

        @Override
        public void close() {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
