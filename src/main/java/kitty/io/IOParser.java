package kitty.io;

import java.time.LocalDate;

public class IOParser {

    public static String getDataType(String rawData) {
        return rawData.substring(0, rawData.indexOf("|"));
    }

    public static String getDataStatus(String rawData) {
        return rawData.substring(rawData.indexOf("|") + 1, rawData.indexOf("|") + 2);
    }

    public static String getDataTask(String rawData) {
        return rawData.substring(rawData.indexOf("|") + 3);
    }

    public static boolean isTaskDone(String status) {
        return status.equals("1");
    }

    public static String getTaskName(String task) {
        return task.substring(0, task.indexOf("|"));
    }

    public static LocalDate getTaskDate(String task) {
        String dateAsString = task.substring(task.indexOf("|") + 1);
        return LocalDate.parse(dateAsString);
    }
}
