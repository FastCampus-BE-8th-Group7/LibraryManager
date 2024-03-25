package reservation;

import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ReservationDAO reservationDAO = new ReservationDAO();

        while (true) {
            System.out.println("1. 도서 검색");
            System.out.println("2. 도서 예약");
            System.out.println("3. 예약 확인");
            System.out.println("4. 예약 취소");
            System.out.println("5. 종료");
            System.out.print("원하시는 기능을 선택해주세요: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // 개행 문자 제거

            switch (choice) {
                case 1:
                    System.out.print("검색할 도서 제목을 입력하세요: ");
                    String bookTitle = scanner.nextLine();
                    System.out.println("검색 결과:");
                    reservationDAO.searchBooks(bookTitle).forEach(System.out::println);
                    break;
                case 2:
                    System.out.print("사용자 ID를 입력하세요: ");
                    int userId = scanner.nextInt();
                    System.out.print("예약할 도서 ID를 입력하세요: ");
                    int bookId = scanner.nextInt();
                    reservationDAO.makeReservation(userId, bookId);
                    break;
                case 3:
                    System.out.print("확인할 사용자 ID를 입력하세요: ");
                    int checkUserId = scanner.nextInt();
                    reservationDAO.confirmReservation(checkUserId);
                    break;
                case 4:
                    System.out.print("취소할 예약 ID를 입력하세요: ");
                    int cancelReservationId = scanner.nextInt();
                    reservationDAO.cancelReservation(cancelReservationId);
                    break;
                case 5:
                    System.out.println("프로그램을 종료합니다.");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
            }
        }
    }
}

