package book;

import bookDto.BookDto;

import java.util.Date;

public class PhysicalBook implements Book{
    private int bookId;
    private String title;
    private String author;
    private Date publicationYear;
    private Long ISBN;
    public Category category;
    private boolean is_available;

    private String location;


    @Override
    public void addBook(BookDto bookDto) {

    }

    @Override
    public int updateBook(int bookId) {
        return 1;
    }

    @Override
    public int deleteBook(int bookId) {
        return 1;
    }
}
