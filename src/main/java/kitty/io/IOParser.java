package kitty.io;

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

    public static String getTaskDate(String task) {
        return task.substring(task.indexOf("|") + 1);
    }
}
