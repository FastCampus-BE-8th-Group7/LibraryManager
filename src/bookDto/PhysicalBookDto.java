package bookDto;

import book.Category;

public class PhysicalBookDto extends BookDto {
    private String location;

    public PhysicalBookDto(String title, String author, int publicationYear, Long ISBN, Category category, boolean is_available, String location) {
        super(title, author, publicationYear, ISBN, category, is_available);
        this.location=location;
    }
}
