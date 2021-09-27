package duke.manager;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private static final String FILE_PATH = "duketasks.txt";
    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm";
    private static final String TASK_TODO = "T";
    private static final String TASK_DEADLINE = "D";
    private static final String TASK_EVENT = "E";
    private static final String TASK_DONE = "1";
    private static final String DELIMITER_STORAGE = "\\|";
    private static final int INDEX_TASKTYPE = 0;
    private static final int INDEX_TASKISDONE = 1;
    private static final int INDEX_TASKDESCRIPTION = 2;
    private static final int INDEX_TASKDATETIME = 3;

    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);

    /**
     * Initial function for getting stored tasks
     * in the duketasks.txt file
     *
     * @param tasks Arraylist of tasks
     */
    public static void getStoredData(ArrayList<Task> tasks) {
        try {
            loadTasksFromFile(tasks);
        } catch (FileNotFoundException e) {
            createFile();
        } catch (Exception e) {
            printStorageError();
        }
    }

    private static void printStorageError() {
        System.out.println("Error in opening duketasks.txt");
    }

    /**
     * Loads tasks from
     * duketasks.txt file
     *
     * @param tasks Arraylist of tasks
     */
    private static void loadTasksFromFile(ArrayList<Task> tasks) throws FileNotFoundException {
        File storedTasks = new File(FILE_PATH);
        Scanner scanner = new Scanner(storedTasks);
        while (scanner.hasNext()) {
            loadStoredTasks(scanner.nextLine(), tasks);
        }
    }

    /**
     * Creates a file
     * duketasks.txt if it does
     * not already exist
     */
    private static void createFile() {
        try {
            File newFile = new File(FILE_PATH);
            newFile.createNewFile();
        } catch (IOException ioException) {
            createFileError(ioException);
        }
    }

    private static void createFileError(IOException ioException) {
        ioException.printStackTrace();
        System.out.println("Cannot create file");
    }

    /**
     * Loads a task as an object in the program
     */
    private static void loadStoredTasks(String input, ArrayList<Task> tasks) {
        String taskType = computeTaskType(input);
        String taskIsDone = computeTaskIsDone(input);
        String taskDescription = computeTaskDescription(input);

        switch (taskType) {
        case TASK_TODO:
            createTodoObject(tasks, taskDescription);
            break;
        case TASK_DEADLINE:
            createDeadlineObject(tasks, taskDateTime(input), taskDescription);
            break;
        case TASK_EVENT:
            createEventObject(tasks, taskDateTime(input), taskDescription);
            break;
        default:
        }
        markTaskAsDone(tasks, taskIsDone);
    }

    private static String taskDateTime(String input) {
        String[] splitString = input.split(DELIMITER_STORAGE);
        return splitString[INDEX_TASKDATETIME].trim();
    }

    private static void markTaskAsDone(ArrayList<Task> tasks, String taskIsDone) {
        if (taskIsDone.equals(TASK_DONE)) {
            tasks.get(size(tasks)).markDone();
        }
    }

    private static int size(ArrayList<Task> tasks) {
        return tasks.size() - 1;
    }

    private static String computeTaskType(String input) {
        String[] splitString = input.split(DELIMITER_STORAGE);
        return splitString[INDEX_TASKTYPE].trim();
    }

    private static String computeTaskIsDone(String input) {
        String[] splitString = input.split(DELIMITER_STORAGE);
        return splitString[INDEX_TASKISDONE].trim();
    }

    private static String computeTaskDescription(String input) {
        String[] splitString = input.split(DELIMITER_STORAGE);
        return splitString[INDEX_TASKDESCRIPTION].trim();
    }

    private static void createEventObject(ArrayList<Task> tasks, String s, String taskDescription) {
        LocalDateTime eventAt = LocalDateTime.parse(s.strip(), formatter);
        tasks.add(new Event(taskDescription, eventAt, s));
    }

    private static void createDeadlineObject(ArrayList<Task> tasks, String s, String taskDescription) {
        LocalDateTime deadlineBy = LocalDateTime.parse(s.strip(), formatter);
        tasks.add(new Deadline(taskDescription, deadlineBy, s));
    }

    private static void createTodoObject(ArrayList<Task> tasks, String taskDescription) {
        tasks.add(new Todo(taskDescription));
    }

    /**
     * Saves tasks from program to duketasks.txt file
     *
     * @param tasks
     */
    public static void saveTasksToFile(ArrayList<Task> tasks) {
        try {
            FileWriter fileWriter = new FileWriter(FILE_PATH);
            for (Task task : tasks) {
                String toAdd = task.fileDescription() + "\n";
                fileWriter.write(toAdd);
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Cannot save data to " + FILE_PATH);
        }
    }
}
