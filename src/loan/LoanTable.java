package loan;

import java.util.HashMap;

public final class LoanTable {
    private static LoanTable instance;
    public static HashMap<Integer, Loan> loanMap;

    private LoanTable() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        loanMap = new HashMap<>();
    }

    public static LoanTable getInstance() {
        if (instance == null) {
            instance = new LoanTable();
        }
        return instance;
    }

    public static void createTuple(Loan n) {
        loanMap.put(n.getLoanId(), n);
    }

    public static HashMap<Integer, Loan> getLoanMap() {
        return loanMap;
    }

    public static Loan getLoan(int loanId) {
        return loanMap.get(loanId);
    }
}