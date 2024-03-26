package bookDto;

import book.Category;

public class EBookDto extends BookDto {

    public EBookDto(String title, String author, int publicationYear, Long ISBN, Category category, boolean is_available) {
        super(title, author, publicationYear, ISBN, category, is_available);
    }
}

