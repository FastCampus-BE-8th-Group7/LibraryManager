package book;

import bookDto.BookDto;

import java.util.Date;

public interface Book {
    void addBook(BookDto bookDto);
    int updateBook(int bookId); // 책 이름, 작가 이름, 위치 등 수정하기
    int deleteBook(int bookId);
    // search()
}
