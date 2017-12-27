package hello.model;


public class StockRecord {
    private AccessionNo accessionNo;
    private Book book;
    private Status status;

    private StockRecord(AccessionNo accessionNo, Book book, Status status) {
        this.accessionNo = accessionNo;
        this.book = book;
        this.status = status;
    }

    public static StockRecord create(String accessionNo, Book book) {
        return new StockRecord(AccessionNo.create(accessionNo),book,Status.AVAILABLE);
    }

    public static StockRecord create(String accessionNo, Book book, String status) {
        return new StockRecord(AccessionNo.create(accessionNo),book,Status.getStatus(status));
    }

    public static StockRecord remove(String accessionNo) {
        return new StockRecord(AccessionNo.create(accessionNo), null, Status.WITHDRAWN);
    }

    public AccessionNo getAccessionNo() {
        return accessionNo;
    }

    public Book getBook() {
        return book;
    }

    public Status getStatus() {
        return status;
    }
}
