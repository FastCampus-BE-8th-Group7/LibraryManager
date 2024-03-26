package book;

import java.util.HashMap;
import java.util.Map;

public class BookDB {
    private Map<Integer, Book> db;
    private BookDB() {
        db=new HashMap<>();
    }
    private static class LazyHolder {
        public static final BookDB INSTANCE = new BookDB();
    }

    public static BookDB getInstance() {
        return LazyHolder.INSTANCE;
    }
    // db에 데이터 추가
    public void put(int key, Book value) {
        db.put(key, value);
    }

    // db에서 데이터 조회
    public Book get(int key) {
        return db.get(key);
    }

    // db에서 데이터 삭제
    public void remove(String key) {
        db.remove(key);
    }

    // db 초기화
    public void clear() {
        db.clear();
    }

    // db 크기 반환
    public int size() {
        return db.size();
    }
}
