package hello.model;

public class AccessionNo {

    private String accessionNo;

    private AccessionNo(String accessionNo) {
        this.accessionNo = accessionNo;
    }

    public static AccessionNo create(String accessionNo) {
        return new AccessionNo(accessionNo);
    }

    public String getAccessionNo() {
        return this.accessionNo;
    }

}
