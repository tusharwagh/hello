package hello.model;

public class Book {

    private ISBN isbn;
    private String title;

    private Book(ISBN isbn, String title) {
        this.isbn = isbn;
        this.title = title;
    }

    public static Book create(String isbnNumber, String title) {
        return new Book(ISBN.create(isbnNumber),title);
    }

    public static ISBN remove(String isbnNumber) {
        return ISBN.create(isbnNumber);
    }

    public ISBN getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }
}
