package loan;

import java.time.LocalDate;

public class Loan {
    private int loanId;
    private int bookId;
    private int userId;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate returnDate;

    // 생성자
    public Loan(int bookId, int userId){
        //loanId = setLoanId();
        this.bookId = bookId;
        this.userId = userId;
        startDate = LocalDate.now();
        endDate = startDate.plusDays(7);
        // returnDate = none? 반납 아직 안했으면 null 했으면 반납한 날짜
    }

    public void getState() {
        System.out.printf("%d님이 %d를 %s에 빌리셨습니다.\n", userId, bookId, startDate);
        System.out.printf("반납 예정일은 %s 입니다.\n",endDate);
    }

    public int getUserId() {
        return userId;
    }

    public int getBookId() {
        return bookId;
    }

    public int getLoanId() { return loanId; }
    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public LocalDate getReturnDate() { return returnDate; }

    /**
     * @deprecated
     * auto increment를 제공하므로 loanId를 직접 삽입할 필요 없음
     */
    public int setLoanId() {
        LoanTable loanTable = LoanTable.getInstance();
        int index = loanTable.loanMap.size();
        return index;
    }

    public void setReturnDate() {
        this.returnDate = LocalDate.now();
    }
}