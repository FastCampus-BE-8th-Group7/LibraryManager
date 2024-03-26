package book;

import bookDto.BookDto;

import java.util.Date;

public class PhysicalBook {
    private int bookId;
    private String title;
    private String author;
    private Date publicationYear;
    private Long ISBN;
    public Category category;
    private boolean is_available;

    private String location;

}
