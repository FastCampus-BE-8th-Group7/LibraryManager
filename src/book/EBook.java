package book;


import bookDto.BookDto;

import java.util.Date;

public class EBook implements Book{
    private int bookId;
    private String title;
    private String author;
    private Date publicationYear;
    private Long ISBN;
    public Category category;
    private boolean is_available;

    @Override
    public void addBook(BookDto bookDto) {

    }

    @Override
    public int updateBook(int bookId) {
        return 0;
    }

    @Override
    public int deleteBook(int bookId) {
        return 0;
    }
}
