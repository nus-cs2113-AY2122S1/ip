package duke.components;

import duke.exceptions.StorageException;
import duke.task.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;

import static duke.constants.Messages.HORIZONTAL_LINE;
import static duke.constants.Messages.MESSAGE_FILE_NOT_FOUND;
import static duke.constants.Messages.MESSAGE_STORAGE_EXCEPTION;
import static duke.constants.StorageConfig.PATH_TO_STORAGE_FILE;

/**
 * A class that deals with loading tasks from the file and saving tasks in the file.
 */
public class Storage {

    static Parser parser = new Parser();

    /**
     * Creates a new file to store data.
     *
     * @throws IOException If there are failed or interrupted I/O operations.
     */
    public static void createFile() throws IOException {
        File file = new File(String.valueOf(PATH_TO_STORAGE_FILE));
        if (file.exists()) {
            file.delete();
        }
        file.getParentFile().mkdirs();
        file.createNewFile();
    }

    /**
     * Reads data that stored in the storage file.
     *
     * @return The data stored in storage and an empty ArrayList of Task if there is something wrong
     * with the data file.
     * @throws IOException If there are failed or interrupted I/O operations.
     */
    public static ArrayList<Task> readDataFromFile() throws IOException {
        if (Files.notExists(PATH_TO_STORAGE_FILE)) {
            createFile();
        }
        ArrayList<Task> storedTasks = new ArrayList<>();
        File file = new File(String.valueOf(PATH_TO_STORAGE_FILE));
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                Task task;
                task = parser.retrieveStoredData(scanner.nextLine());
                storedTasks.add(task);
            }
        } catch (FileNotFoundException e) {
            System.out.println(HORIZONTAL_LINE);
            System.out.println(MESSAGE_FILE_NOT_FOUND);
            return new ArrayList<Task>();
        } catch (StorageException e) {
            createFile();
            System.out.println(HORIZONTAL_LINE);
            System.out.println(MESSAGE_STORAGE_EXCEPTION);
            return new ArrayList<Task>();
        }
        return storedTasks;
    }

    /**
     * Saves data to the storage file.
     *
     * @param tasksList The ArrayList of Task to be stored.
     * @throws IOException If there are failed or interrupted I/O operations.
     */
    public static void saveDataToFile(TaskList tasksList) throws IOException {
        FileWriter fileWriter = new FileWriter(String.valueOf(PATH_TO_STORAGE_FILE));
        for (Task t : tasksList.getTasksList()) {
            fileWriter.write(parser.formatTaskToStore(t));
        }
        fileWriter.close();
    }
}
