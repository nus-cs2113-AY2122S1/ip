package duke.storage;

import duke.data.TaskList;
import duke.exception.IllegalValueException;
import duke.exception.InvalidStorageFilePathException;
import duke.exception.StorageOperationException;
import duke.task.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {

    /** Default file path used if the user doesn't provide the file name. */
    public static final String DEFAULT_STORAGE_FILEPATH = "data/duke.txt";

    private final Path path;

    public Storage() throws InvalidStorageFilePathException {
        this(DEFAULT_STORAGE_FILEPATH);
    }

    public Storage(String filePath) throws InvalidStorageFilePathException {
        this.path = Paths.get(filePath);
        if (!isValidPath(path)) {
            throw new InvalidStorageFilePathException("Storage file should end with '.txt'");
        }
    }

    private static boolean isValidPath(Path filePath) {
        return filePath.toString().endsWith(".txt");
    }

    public void save(TaskList tasks) throws StorageOperationException {
        try {
            List<String> encodedTaskList = TaskListEncoder.encodeTaskList(tasks);
            Files.write(path, encodedTaskList);
        } catch (IOException ioe) {
            throw new StorageOperationException("Error writing to file: " + path);
        }
    }

    public TaskList load() throws StorageOperationException {

        if (!Files.exists(this.path) || !Files.isRegularFile(this.path)) {
            return new TaskList();
        }

        try {
            System.out.println("Loading..." + System.lineSeparator() + "███████████████ 100%");
            return TaskListDecoder.decodeTaskList(Files.readAllLines(this.path));
        } catch (FileNotFoundException fnfe) {
            throw new AssertionError("A non-existent file scenario is already handled earlier.");
            // other errors
        } catch (IOException ioe) {
            throw new StorageOperationException("Error writing to file: " + this.path);
        } catch (IllegalValueException ive) {
            throw new StorageOperationException("File contains illegal data values; Data type constraints not met");
        }
    }

    public String getPath() {
        return path.toString();
    }

}
