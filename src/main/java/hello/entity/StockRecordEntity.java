package hello.entity;

import hello.model.StockRecord;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="STOCKRECORD")
public class StockRecordEntity {

    @Id
    @Column(name="ACCESSIONNO")
    private String accessionNo;
    @Column(name = "ISBN", nullable = false)
    private String isbn;
    @Column(name = "STATUS")
    private String status;

    public StockRecordEntity() {
    }

    public StockRecordEntity(StockRecord stockRecord) {
        this.accessionNo = stockRecord.getAccessionNo().getAccessionNo();
        this.isbn = stockRecord.getBook().getIsbn().getIsbnNumber();
        this.status = stockRecord.getStatus().getStatus();
    }

    public StockRecordEntity(String accessionNo, String isbn, String status) {
        this.accessionNo = accessionNo;
        this.isbn = isbn;
        this.status = status;
    }

    public void setAccessionNo(String accessionNo) {
        this.accessionNo = accessionNo;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAccessionNo() {
        return accessionNo;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "StockRecordEntity{" +
                "accessionNo='" + accessionNo + '\'' +
                ", isbn='" + isbn + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
