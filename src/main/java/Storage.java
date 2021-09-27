import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.io.FileNotFoundException;

/**
 * Storage class facilitates loading tasks from the file and saving tasks in the file.
 */
public class Storage {

    private static final String STORAGE_PATH = "duke.txt";
    private static final int FILE_ERROR_EXIT_CODE = 0;
    public static final char COMPLETE_CHARACTER = 'X';

    /**
     * Loads stored tasks from file.
     *
     * @param storedTasks File at STORED_PATH.
     * @throws FileNotFoundException If the file at STORED_PATH does not exist.
     */
    private static void loadStoredTasks(File storedTasks) throws FileNotFoundException {
        Scanner scanner = new Scanner(storedTasks);
        try {
            scanner.nextLine();
        } catch (NoSuchElementException e) {
            System.out.println(STORAGE_PATH + " is empty");
        }

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            char completionStatus = line.charAt(0);
            String taskDescription = line.substring(1);

            try {
                Task newTask = TaskList.createTask(taskDescription);
                TaskList.storeTask(newTask, false);

            } catch (DukeException e) {
                System.out.println(STORAGE_PATH + " is corrupted. Please delete duke.txt and restart the program.");
                System.exit(FILE_ERROR_EXIT_CODE);
            }
            if (completionStatus == COMPLETE_CHARACTER) {
                int TaskListSize = TaskList.getTaskListSize();
                TaskList.markComplete(TaskListSize, false);
            }
        }
    }

    /**
     * Loads pre-existing tasks from file at STORAGE_PATH or creates a new file if it does not exist.
     */
    public static void setupStorage() {
        try {
            File storedTasks = new File(STORAGE_PATH);
            if (storedTasks.createNewFile()) {
                System.out.println("File created: " + storedTasks.getName());
            } else {
                System.out.println("Loading from " + STORAGE_PATH);
                loadStoredTasks(storedTasks);
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error in reading " + STORAGE_PATH + " occurred.");
            System.exit(FILE_ERROR_EXIT_CODE);
        } catch (IOException e) {
            System.out.println("An error in creating " + STORAGE_PATH + " occurred.");
            System.exit(FILE_ERROR_EXIT_CODE);
        }
    }

    /**
     * Overwrites the file at STORAGE_PATH with the tasks from the inputTasks list in the TaskList class.
     */
    public static void writeTasksToFile() {
        try {
            FileWriter fw = new FileWriter(STORAGE_PATH, false);
            ArrayList<Task> inputTasks = TaskList.getInputTasks();
            for (Task task : inputTasks) {
                fw.write('\n' + task.getEncodedFormat());
            }
            fw.close();
        } catch (IOException e) {
            System.out.println(STORAGE_PATH + " can't be overwritten. Changes will be lost when the program is closed");
        }
    }

    /**
     * Appends a new task to the file at STORAGE_PATH.
     *
     * @param newTask Task to append to the file at STORAGE_PATH.
     */
    public static void appendTaskToFile(Task newTask) {
        try {
            FileWriter fw = new FileWriter(STORAGE_PATH, true);
            fw.write('\n' + newTask.getEncodedFormat());
            fw.close();
        } catch (IOException e) {
            System.out.println(STORAGE_PATH + " cannot be updated. Changes will be lost when the program is closed");
        }
    }
}
