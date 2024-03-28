package loan;

import book.Book;
import user.User;
import user.UserController;

import java.time.LocalDate;
import java.util.*;

public class LoanController {
    LoanDAO loanDAO = new LoanDAO();
    UserController userController = new UserController();

    // checkBookLoanState
    public void checkBookLoanState(String bookTitle) {
        Book book = loanDAO.getLoanStatusForBook(bookTitle);
        if (book==null) {
            System.out.printf("%s 도서를 찾을 수 없습니다.\n", bookTitle);
        }
        else if (book.getIsAvailable()) {
            System.out.printf("%s 도서는 대출 가능합니다.\n", bookTitle);
        } else {
            System.out.printf("%s 도서는 대출중입니다.\n", bookTitle);
        }
    }

    // checkUserLoanState
    public void checkUserLoanState(int userId) {
        // user data 조회
        User user = userController.getUser(userId);

        System.out.printf("***** %s 님의 대출 목록 *****\n", user.getName());
        System.out.printf("| %-30s | %-10s | %-10s | %-15s | %-10s | %-10s | %-10s |\n", "책 제목", "저자", "출판 연도", "ISBN", "출판사", "카테고리", "반납일");
        loanDAO.getLoanHistoryForUser(userId);
        HashMap<Book, LocalDate> loanHistory = loanDAO.getLoanHistoryForUser(userId);

        List<Map.Entry<Book, LocalDate>> entryList = new LinkedList<>(loanHistory.entrySet());
        entryList.sort(Map.Entry.comparingByValue());

        for (Map.Entry<Book, LocalDate> entry : entryList) {
            Book book = entry.getKey();
            LocalDate return_date = entry.getValue();
            if (return_date.equals(LocalDate.of(3000, 1, 1)))
                System.out.printf("| %-30s | %-10s | %-10s | %-15s | %-10s | %-10s | %-10s |\n",
                        book.getTitle(),
                        book.getAuthor(),
                        book.getPublicationYear(),
                        book.getIsbn(),
                        book.getPublisher(),
                        book.getCategory(),
                        "대출중");
            else {
                System.out.printf("| %-30s | %-10s | %-10s | %-15s | %-10s | %-10s | %-10s |\n",
                        book.getTitle(),
                        book.getAuthor(),
                        book.getPublicationYear(),
                        book.getIsbn(),
                        book.getPublisher(),
                        book.getCategory(),
                        return_date);
            }
        }
    }

    // loanBook
    public void loanBook(String bookTitle, int userId) {
        User user = userController.getUser(userId);
        String result = loanDAO.createLoanHistory(bookTitle, userId);
        if(result==null){
            System.out.printf("%s 도서를 찾을 수 없습니다.\n", bookTitle);
        }
        else if(result.equals("-1")) {
            System.out.printf("%s 님은 대출중입니다.\n", user.getName());
        }
        else {
            System.out.printf("-- %s 님의 대출 요청\n", user.getName());
            System.out.println(result);
        }
    }

    // returnBook
    public void returnBook(int userId) {
        User user = userController.getUser(userId);
        String result = loanDAO.updateLoanHistoryForReturn(userId);

        System.out.printf("-- %s 님의 반납 요청\n", user.getName());
        System.out.println(result);
    }
}