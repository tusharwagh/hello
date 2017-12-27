package hello.model;

public enum Status {
    AVAILABLE("Available"),
    ISSUED("Issued"),
    MISSING("Missing"),
    WITHDRAWN("Withdrawn");

    private String status;

    private Status(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public static Status getStatus(String status) {
        for(Status lStatus : Status.values()) {
            if(lStatus.getStatus().equals(status)) {
                return lStatus;
            }
        }
        return null;
    }
}
