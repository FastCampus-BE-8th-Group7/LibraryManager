package bookDto;

import book.Category;

import java.util.Date;

public class BookDto {
    private String title;
    private String author;
    private int publicationYear;
    private Long ISBN;
    private Category category;
    private boolean is_available;

    public BookDto(String title,
                   String author,
                   int publicationYear,
                   Long ISBN,
                   Category category,
                   boolean is_available) {
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.ISBN = ISBN;
        this.category = category;
        this.is_available = is_available;
    }

    @Override
    public String toString() {
        return "BookDto{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", publicationYear=" + publicationYear +
                ", ISBN=" + ISBN +
                ", category=" + category +
                ", is_available=" + is_available +
                '}';
    }
}
