import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.System.lineSeparator;

public class Storage {
    public static final int TRUE_VALUE = 1;
    public static final int FALSE_VALUE = 0;
    public static final int RESULT_TASK_TYPE = 0;
    public static final int RESULT_IS_DONE = 1;
    public static final int RESULT_DESCRIPTION = 2;
    public static final int RESULT_TIME = 3;

    public static void loadData(Scanner readFile) {
        while (readFile.hasNext()) {
            String information = readFile.nextLine();
            parseInformation(information);
        }
    }

    private static void parseInformation(String information) {
        String[] result = information.split(" \\| ");
        checkTaskType(result);
    }

    private static void checkTaskType(String[] result) {
        Task newTask;
        switch (result[RESULT_TASK_TYPE]) {
        case "T":
            newTask = new Todo(result[RESULT_DESCRIPTION],
                    strToBoolean(result[RESULT_IS_DONE]));
            TaskList.reloadTask(newTask);
            break;
        case "D":// need to edit this
            newTask = new Deadline(result[RESULT_DESCRIPTION],
                    strToBoolean(result[RESULT_IS_DONE]), parseDeadline(result[RESULT_TIME]));
            TaskList.reloadTask(newTask);
            break;
        case "E":// need to edit this
            newTask = new Event(result[RESULT_DESCRIPTION],
                    strToBoolean(result[RESULT_IS_DONE]), parseEvent(result[RESULT_TIME]));
            TaskList.reloadTask(newTask);
            break;
        }
    }


    private static boolean strToBoolean(String s) {
        return !s.equals("0");
    }


    public static void storeData(FileWriter fileWrite) throws IOException {
        ArrayList<Task> list = TaskList.getList();
        for (Task task : list) {
            fileWrite.write(parseTask(task) + lineSeparator());
        }
        fileWrite.close();
    }

    private static String parseTask(Task task) {
        String newString;
        newString = task.getTaskType() + " | " + booleanInt(task.isDone) +
                " | " + task.getDescription();
        if (task instanceof Event) {
            Event event = (Event) task;
            newString = newString + " | " + event.getDate();
        } else if (task instanceof Deadline) {
            Deadline deadline = (Deadline) task;
            newString = newString + " | " + deadline.getDate();
        }
        return newString;
    }

    public static LocalDateTime parseDeadline (String result) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
        LocalDateTime date = LocalDateTime.parse(result, formatter);
        return date;
    }
    public static LocalDateTime[] parseEvent (String result) {
        String[] results = result.split(" to ");
        LocalDateTime[] dates =  new LocalDateTime[2];
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
        dates[0] = LocalDateTime.parse(results[0], formatter);
        dates[1] = LocalDateTime.parse(results[1], formatter);
        return dates;
    }

    private static int booleanInt(boolean isDone) {
        return isDone ? TRUE_VALUE : FALSE_VALUE;
    }
}
