package loan;

import book.Book;
import book.BookTable;
import user.User;
import user.UserController;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class LoanController {
    LoanTable loanTable = LoanTable.getInstance();
    BookTable bookTable = BookTable.getInstance();
    LoanDAO loanDAO = new LoanDAO();
    UserController userController = new UserController();

    // checkBookLoanState
    public void checkBookLoanState(int bookId) {
        HashMap<Integer, Loan> loanMap = loanTable.loanMap;
        Collection<Loan> values = loanMap.values();
        boolean state = false;
        Book book = BookTable.bookMap.get(bookId);

        for(Loan loan: values) {
            if(loan.getBookId()==bookId && loan.getReturnDate()!=null) {
                state = true;
                break;
            }
        }

        if (state) {
            System.out.println(book.getTitle() + "책은 대출중입니다.");
        } else {
            System.out.println(book.getTitle() + "책은 대출 가능합니다.");
        }
    }

    // checkUserLoanState
    public void checkUserLoanState(int userId) {
        // user data 조회
        User user = userController.getUser(userId);

        System.out.printf("***** %s 님의 대출 목록 *****\n", user.getName());
        System.out.printf("| %-30s | %-10s | %-10s | %-15s | %-10s |\n", "책 제목", "저자", "출판사", "ISBN", "출판일");
        loanDAO.getLoanHistoryForUser(userId);
        List<Book> books = loanDAO.getLoanHistoryForUser(userId);

        for (Book book : books) {
            System.out.printf("| %-28s | %-10s | %-10s | %-15s | %-10s |\n",
                    book.getTitle(), book.getAuthor(), book.getPublisher(), book.getIsbn(), book.getPublicationYear());

        }
    }

    // loanBook
    public void loanBook(int bookId, int userId) {
        User user = userController.getUser(userId);
        String result = loanDAO.createLoanHistory(bookId, userId);

        System.out.printf("-- %s 님의 대출 요청\n", user.getName());
        System.out.println(result);
    }

    // returnBook
    public void returnBook(int userId) {
        User user = userController.getUser(userId);
        String result = loanDAO.updateLoanHistoryForReturn(userId);

        System.out.printf("-- %s 님의 반납 요청\n", user.getName());
        System.out.println(result);
    }
}