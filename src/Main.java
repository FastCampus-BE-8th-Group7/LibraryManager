import db.Database;
import user.*;
import book.*;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Database.connect();

        UserController userController = new UserController();
        UserView userView = new UserView();
        BookController bookController = new BookController();
        BookView bookView = new BookView();
        Scanner scanner = new Scanner(System.in);

        outerWhile:
        while (true) {
            System.out.println("*****도서관 시스템에 오신것을 환영합니다*****");
            System.out.println("*****로그인 후 이용가능합니다.*****");
            System.out.println("1. 회원 등록");
            System.out.println("2. 로그인");
            System.out.println("0. 종료");
            System.out.print("원하는 서비스 번호를 입력하세요: ");

            int number;
            if (scanner.hasNextInt()) {
                number = scanner.nextInt();
                if (number == 1) {
                    System.out.println("*****회원 등록을 진행합니다. 정보를 입력해주세요.*****");
                    System.out.print("이름: ");
                    String name = scanner.next();
                    System.out.print("전화번호: ");
                    String phone = scanner.next();
                    System.out.print("이메일: ");
                    String email = scanner.next();
                    System.out.print("비밀번호: ");
                    String password = scanner.next();
                    User user = userController.registerUser(name, phone, email, password);
                    System.out.println();
                } else if (number == 2) {
                    System.out.println("*****로그인을 진행합니다.*****");
                    System.out.print("이메일: ");
                    String email = scanner.next();
                    System.out.print("비밀번호: ");
                    String password = scanner.next();
                    User user = userController.loginUser(email, password);
                    if (user == null) {
                        System.out.println("*****로그인을 실패했습니다.*****");
                        continue;
                    }
                    innerWhile:
                    while (true) {
                        System.out.println("*****로그인을 성공했습니다.*****");
                        int myUserId = user.getUserId();
                        System.out.println("1. 내 정보 보기");
                        System.out.println("2. 내 정보 수정하기");
                        System.out.println("3. 책 검색하기");
                        System.out.println("4. 책 등록하기");
                        System.out.println("5. 책 수정하기");
                        System.out.println("6. 책 삭제하기");
                        System.out.println("7. 내 대출보기");
                        System.out.println("8. 내 예약보기");
                        System.out.println("9. 로그아웃하기");
                        System.out.println("10. 탈퇴하기");
                        System.out.print("원하는 서비스 번호를 입력하세요: ");
                        scanner = new Scanner(System.in);
                        if (scanner.hasNextInt()) {
                            number = scanner.nextInt();
                            switch (number) {
                                case 1:
                                    System.out.println("*****내 정보 보기를 진행합니다.*****");
                                    user = userController.getUser(myUserId);
                                    if (user != null) {
                                        userView.viewUserInfo(user);
                                    }
                                    break;
                                case 2:
                                    System.out.println("*****내 정보 수정을 진행합니다. 정보를 입력해주세요.*****");
                                    System.out.print("이름: ");
                                    String name = scanner.next();
                                    System.out.print("전화번호: ");
                                    String phone = scanner.next();
                                    System.out.print("이메일: ");
                                    email = scanner.next();
                                    System.out.print("비밀번호: ");
                                    password = scanner.next();
                                    userController.updateUser(myUserId, name, phone, email, password);
                                    break;
                                case 3:
                                    System.out.println("*****책 검색을 진행합니다.*****");
                                    System.out.print("책 이름: ");
                                    name = scanner.next();
                                    List<Book> searchedBooks = bookController.getSearchBooks(name);
                                    bookView.viewSearchBooks(searchedBooks, name);
                                    break;
                                case 4:
                                    System.out.println("*****책 등록을 진행합니다.*****");
                                    System.out.print("책제목: ");
                                    scanner.nextLine();
                                    String title = scanner.nextLine();
                                    System.out.print("저자: ");
                                    String author = scanner.nextLine();
                                    System.out.print("출간연도: ");
                                    int year = scanner.nextInt();
                                    System.out.print("출간월: ");
                                    int month = scanner.nextInt();
                                    System.out.print("출간일: ");
                                    int dayOfMonth = scanner.nextInt();
                                    System.out.print("ISBN: ");
                                    String isbn = scanner.next();
                                    System.out.print("출판사: ");
                                    String publisher = scanner.next();
                                    System.out.print("분야: ");
                                    String category = scanner.next();
                                    bookController.registerBook(title, author, LocalDate.of(year, month, dayOfMonth), isbn, true, publisher, category);
                                    break;
                                case 5:
                                    System.out.println("*****책 수정을 진행합니다.*****");
                                    System.out.print("수정할 책 번호 입력: ");
                                    number = scanner.nextInt();
                                    System.out.println("*****정보를 입력해주세요.*****");
                                    System.out.print("책제목: ");
                                    scanner.nextLine();
                                    title = scanner.nextLine();
                                    System.out.print("저자: ");
                                    author = scanner.nextLine();
                                    System.out.print("출간년도: ");
                                    year = scanner.nextInt();
                                    System.out.print("출간월: ");
                                    month = scanner.nextInt();
                                    System.out.print("출간일: ");
                                    dayOfMonth = scanner.nextInt();
                                    System.out.print("ISBN: ");
                                    isbn = scanner.next();
                                    System.out.print("출판사: ");
                                    publisher = scanner.next();
                                    System.out.print("분야: ");
                                    category = scanner.next();
                                    bookController.updateBook(number, title, author, LocalDate.of(year, month, dayOfMonth), isbn, true, publisher, category);
                                    break;
                                case 6:
                                    System.out.println("*****책 삭제를 진행합니다.*****");
                                    System.out.print("삭제할 책 번호 입력: ");
                                    number = scanner.nextInt();
                                    bookController.deleteBook(number);
                                    break;
                                case 7:
                                    // TODO: 2024-03-26 내 대출 정보 호출하기
                                case 8:
                                    // TODO: 2024-03-26 내 예약 정보 호출하기
                                case 9:
                                    break innerWhile;
                                case 10:
                                    userController.deleteUser(myUserId);
                                    break innerWhile;
                                default:
                                    System.out.println("제공하는 서비스 번호만 입력하세요.");
                            }
                        } else {
                            System.out.println("숫자를 입력하세요.");
                            scanner.next(); // 잘못된 입력을 버림
                        }


                    }
                } else if (number == 0) {
                    break;
                } else {
                    System.out.println("제공하는 서비스 번호만 입력하세요.");
                }
            } else {
                System.out.println("숫자를 입력하세요.");
                scanner.next(); // 잘못된 입력을 버림
            }
        }
        Database.disconnect();
    }
}