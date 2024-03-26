package loan;

import java.util.Collection;
import java.util.HashMap;

public class LoanController {
    LoanTable loanTable = LoanTable.getInstance();

    // checkBookLoanState
    public void checkBookLoanState(int bookId) {
        HashMap<Integer, Loan> loanMap = loanTable.loanMap;
        Collection<Loan> values = loanMap.values();
        boolean state = false;

        for(Loan loan: values) {
            if(loan.getBookId()==bookId && loan.getReturnDate()!=null) {
                state = true;
                break;
            }
        }

        if (state) {
            System.out.println(bookId + "책은 대출중입니다.");
        } else {
            System.out.println(bookId + "책은 대출 가능합니다.");
        }
    }

    // checkUserLoanState
    public void checkUserLoanState(int userId) {
        HashMap<Integer, Loan> loanMap = loanTable.loanMap;
        Collection<Loan> values = loanMap.values();
        boolean state = false;


        for(Loan loan: values) {
            if(loan.getUserId()==userId && loan.getReturnDate()==null) {
                state = true;
                break;
            }
        }

        if (state) {
            System.out.println(userId + "님은 현재 대출중입니다.");
        } else {
            System.out.println(userId + "님은 현재 대출 가능합니다.");
        }
    }

    // loanBook
    public void loanBook(int bookId, int userId) {
        Loan newLoan = new Loan(bookId, userId);
        loanTable.createTuple(newLoan);

        System.out.println(userId + "님이 책을 대출하였습니다.");
    }

    // returnBook
    public void returnBook(int loanId) {
        Loan returnLoan = loanTable.getLoan(loanId);
        returnLoan.setReturnDate();
        loanTable.loanMap.put(loanId, returnLoan);

        System.out.println(returnLoan.getUserId() + "님이 책을 반납하였습니다.");
    }
}