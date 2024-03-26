package book;

public enum Category {
    UNKNOWN(-1),
    GENERAL_WORK(000),
    PHILOSOPHY(100);
//    RELIGION(200),
//    SOCIAL_SCIENCE(300),
//    NATURAL_SCIENCE(400),
//    TECHNOLOGY_SCIENCE(500),
//    ARTS(600),
//    LANGUAGE(700),
//    LITERATURE(800),
//    HISTORY(900),


    private int value;
    Category(int value) {
        this.value=value;
    }
    public void setValue(int value) {
        this.value=value;
    }
    public static Category fromValue(int value) {
        for (Category category : Category.values()) {
            if (category.value == value) {
                return category;
            }
        }
        return Category.UNKNOWN;
    }
}
