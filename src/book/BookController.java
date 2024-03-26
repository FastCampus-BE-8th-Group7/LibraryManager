package book;

import java.util.HashMap;

public class BookController {
    BookTable bookTable = BookTable.getInstance();
    public void registerBook(String title, String author, String publisher, int category) {
        Book book = new Book(title, author, publisher, category);
        bookTable.createTuple(book);
        System.out.println("책이 등록 되었습니다.");
    }

    public void updateBook(Book tableBook, Book book) {
        int index = tableBook.getBookId();
        book.setBookId(index);
        bookTable.bookMap.put(index, book);
        System.out.println("책 정보가 수정되었습니다.");
    }

    public void deleteBook(Book book) {
        System.out.println("책이 삭제 되었습니다.");
    }

    public HashMap<Integer, Book> selectAllBook() {
        System.out.println("책 정보를 조회합니다.");
        return BookTable.bookMap;
    }

}
