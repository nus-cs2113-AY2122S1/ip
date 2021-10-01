package duke;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.System;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    public static final int TRUE_VALUE = 1;
    public static final int FALSE_VALUE = 0;
    public static final int RESULT_TASK_TYPE = 0;
    public static final int RESULT_IS_DONE = 1;
    public static final int RESULT_DESCRIPTION = 2;
    public static final int RESULT_TIME = 3;
    public static final String Todo = "T";
    public static final String Deadline = "D";
    public static final String Event = "E";

    /**
     * Read all the lines in the file line by line
     * and convert it to task type after reading each line
     *
     * @param readFile file targeted for read operation
     */
    public static void loadData(Scanner readFile) {
        while (readFile.hasNext()) {
            String information = readFile.nextLine();
            parseInformation(information);
        }
    }

    /**
     * Split lines read based on " | " into array
     *
     * @param information string extracted from File
     */
    private static void parseInformation(String information) {
        String[] result = information.split(" \\| ");
        checkTaskType(result);
    }

    /**
     * To take in the letter and check if letters fit the task types
     * T: Todo
     * D: Deadline
     * E: Event
     *
     * @param result an array of string that is being parsed by " | "
     */
    private static void checkTaskType(String[] result) {
        Task newTask;
        switch (result[RESULT_TASK_TYPE]) {
        case Todo:
            newTask = new Todo(result[RESULT_DESCRIPTION],
                    strToBoolean(result[RESULT_IS_DONE]));
            TaskList.reloadTask(newTask);
            break;
        case Deadline:// need to edit this
            newTask = new Deadline(result[RESULT_DESCRIPTION],
                    strToBoolean(result[RESULT_IS_DONE]), parseDeadline(result[RESULT_TIME]));
            TaskList.reloadTask(newTask);
            break;
        case Event:// need to edit this
            newTask = new Event(result[RESULT_DESCRIPTION],
                    strToBoolean(result[RESULT_IS_DONE]), parseEvent(result[RESULT_TIME]));
            TaskList.reloadTask(newTask);
            break;
        default:
            break;
        }
    }


    private static boolean strToBoolean(String s) {
        return !s.equals("0");
    }

    /**
     * Saves data on file after every CRUD operation
     *
     * @param fileWrite File given to be written on
     * @throws IOException when file could not be read or if user deletes file while using the program
     */
    public static void storeData(FileWriter fileWrite) throws IOException {
        ArrayList<Task> list = TaskList.getList();
        for (Task task : list) {
            fileWrite.write(parseTask(task) + System.lineSeparator());
        }
        fileWrite.close();
    }

    /**
     * Stores task in a given format below.
     * Todo:        T | 0 | description
     * Deadline:    D | 0 | description | DD-MM-YYYY HHMM
     * Event:       E | 0 | description | DD-MM-YYYY HHMM to DD-MM-YYYY HHMM
     *
     * @param task task stored in ArrayList<Task>
     * @return newString to store as text in file
     */
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

    /**
     * Takes the string stored inside the file and convert it into a datetime object for creation
     * of task to store into arraylist upon the start of program
     *
     * @param result input of string date to be parsed
     * @return datetime object
     */
    private static LocalDateTime parseDeadline(String result) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
        return LocalDateTime.parse(result, formatter);
    }

    /**
     * Takes the string stored inside the file and convert it into a datetime object for creation
     * of task to store into arraylist upon the start of program
     *
     * @param result input of string date to be parsed
     * @return datetime object
     */
    private static LocalDateTime[] parseEvent(String result) {
        String[] results = result.split(" to ");
        LocalDateTime[] dates = new LocalDateTime[2];
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
        dates[0] = LocalDateTime.parse(results[0], formatter);
        dates[1] = LocalDateTime.parse(results[1], formatter);
        return dates;
    }

    private static int booleanInt(boolean isDone) {
        return isDone ? TRUE_VALUE : FALSE_VALUE;
    }
}
