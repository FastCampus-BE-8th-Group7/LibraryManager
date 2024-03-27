package book;

import java.util.List;

public class BookView {

    public void viewBooks(List<Book> books) {
        if (books.isEmpty()) {
            System.out.println("검색 결과 없음");
            return;
        }
        System.out.printf("%-6s|%-20s|%-15s|%-15s|%-15s|%-10s|%-15s|%-10s\n", "ID", "제목", "저자", "출판 연도", "ISBN", "출판사", "카테고리", "대출 상태");

        for (Book book : books) {
            String availableStatus = book.getIsAvailable() ? "대출 가능" : "대출 불가";
            System.out.printf("%-6d|%-20s|%-15s|%-15s|%-15s|%-10s|%-15s|%-10s\n",
                    book.getBookId() + 1,
                    book.getTitle(),
                    book.getAuthor(),
                    book.getPublicationYear().toString(),
                    book.getIsbn(),
                    book.getPublisher(),
                    book.getCategory().toString(),
                    availableStatus);
        }
    }

    public void viewSearchBooks(List<Book> books, String searchQuery) {
        System.out.println("'" + searchQuery + "'에 대한 검색 결과:");
        viewBooks(books);
    }
}