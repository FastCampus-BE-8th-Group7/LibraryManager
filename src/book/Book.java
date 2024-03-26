package book;

import java.time.LocalDate;

public class Book {
    private int bookId;
    private String title;
    private String author;
    private LocalDate publicationYear;
    private String isbn;
    private boolean isAbailable;
    private String publisher;
    private int category;

    public Book(String title, String author, String publisher, int category) {
        setBookId();
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.category = category;
        isAbailable = true;
    }

    public Book() {}

    public String getTitle() {
        return this.title;
    }

    public String getAuthor() {
        return this.author;
    }

    public String getPublisher() {
        return this.publisher;
    }

    public int getBookId() {
        return this.bookId;
    }

    public void setBookId() {
        bookId = BookTable.bookMap.size();
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public Book setBook(String title, String author, String publisher, int category) {
        Book book = new Book(title, author, publisher, category);
        return book;
    }
}
