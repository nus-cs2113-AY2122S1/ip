package duke.common;

import java.time.format.DateTimeFormatter;

/**
 * Common formats related items to be shared across all classes in duke.
 */
public class CommonFormat {

    final public static String FORMAT_DATE = "yyyy-MM-dd";
    final public static String FORMAT_TIME = "HH:mm";
    final public static String FORMAT_DATETIME = FORMAT_DATE + " " + FORMAT_TIME;

    final public static String FORMAT_DATETIME_PRINT = "MMM dd yyyy, HH:mm";

    final public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMAT_DATETIME);
    final public static DateTimeFormatter formatterDateOnly = DateTimeFormatter.ofPattern(FORMAT_DATE);
    final public static DateTimeFormatter formatterPrint = DateTimeFormatter.ofPattern(FORMAT_DATETIME_PRINT);
}
