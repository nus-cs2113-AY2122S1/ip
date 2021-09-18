package duke.parser;

import java.time.LocalDate;

public class Parser {
    private static final int CHAR_TO_DESCRIPTION = 5;
    
    public static String getDescription(String description) {
        return description;
    }
    
    public static String getDescription(String description, String timeKeyword) {
        return description.substring(0, description.indexOf(timeKeyword));
    }
    
    public static LocalDate getTime(String description, String timeKeyword) {
        String time = description.substring(description.indexOf(timeKeyword) + CHAR_TO_DESCRIPTION);
        return LocalDate.parse(time);
    }
}
