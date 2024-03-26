package book;

import loan.Loan;
import loan.LoanTable;

import java.util.HashMap;

public class BookTable {
    private static BookTable instance;
    public static HashMap<Integer, Book> bookMap;

    private BookTable() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        bookMap = new HashMap<>();
    }

    public static BookTable getInstance() {
        if (instance == null) {
            instance = new BookTable();
        }
        return instance;
    }

    public static void createTuple(Book book) {
        bookMap.put(book.getBookId(), book);
    }

    public static HashMap<Integer, Book> getBookMap() {
        return bookMap;
    }

    public static Book getLoan(int bookId) {
        return bookMap.get(bookId);
    }
}
