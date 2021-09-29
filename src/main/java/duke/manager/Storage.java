package duke.manager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;


public class Storage {
    private static final String FILE_PATH = "duketasks.txt";
    private static final String TASK_TODO = "T";
    private static final String TASK_DEADLINE = "D";
    private static final String TASK_EVENT = "E";
    private static final String TASK_DONE = "1";
    private static final String DELIMITER_STORAGE = "\\|";
    private static final String NEWLINE = "\n";
    private static final int INDEX_TASKTYPE = 0;
    private static final int INDEX_TASKISDONE = 1;
    private static final int INDEX_TASKDESCRIPTION = 2;
    private static final int INDEX_TASKDATETIME = 3;


    /**
     * Transfers the stored tasks from the
     * duketasks.txt file to the program.
     * Creates a new file if one does not
     * exist
     *
     * @param tasks Arraylist of tasks
     * @throws FileNotFoundException If no file found
     * @throws Exception             If cannot access the file
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

    private static void loadTasksFromFile(ArrayList<Task> tasks) throws FileNotFoundException {
        File storedTasks = new File(FILE_PATH);
        Scanner scanner = new Scanner(storedTasks);
        while (scanner.hasNext()) {
            loadStoredTasks(scanner.nextLine(), tasks);
        }
    }

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

    private static void loadStoredTasks(String fileInput, ArrayList<Task> tasks) {
        String taskType = computeTaskType(fileInput);
        String taskIsDone = computeTaskIsDone(fileInput);
        String taskDescription = computeTaskDescription(fileInput);

        switch (taskType) {
        case TASK_TODO:
            createTodoObject(tasks, taskDescription);
            break;
        case TASK_DEADLINE:
            createDeadlineObject(tasks, taskDateTime(fileInput), taskDescription);
            break;
        case TASK_EVENT:
            createEventObject(tasks, taskDateTime(fileInput), taskDescription);
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

    private static void createEventObject(ArrayList<Task> tasks, String dateTime, String eventDescription) {
        LocalDateTime eventAt = LocalDateTime.parse(dateTime.strip(), DateAndTimeParser.inputFormatter);
        tasks.add(new Event(eventDescription, eventAt, dateTime));
    }

    private static void createDeadlineObject(ArrayList<Task> tasks, String dateTime, String deadlineDescription) {
        LocalDateTime deadlineBy = LocalDateTime.parse(dateTime.strip(), DateAndTimeParser.inputFormatter);
        tasks.add(new Deadline(deadlineDescription, deadlineBy, dateTime));
    }

    private static void createTodoObject(ArrayList<Task> tasks, String todoDescription) {
        tasks.add(new Todo(todoDescription));
    }

    /**
     * Saves tasks from program to duketasks.txt file
     *
     * @param tasks Array of tasks
     * @throws IOException If cannot access file
     */
    public static void saveTasksToFile(ArrayList<Task> tasks) {
        try {
            FileWriter fileWriter = new FileWriter(FILE_PATH);
            for (Task task : tasks) {
                String toAdd = task.fileDescription() + NEWLINE;
                fileWriter.write(toAdd);
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Cannot save data to " + FILE_PATH);
        }
    }
}
