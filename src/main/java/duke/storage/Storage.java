package duke.storage;

import duke.exceptions.DukeException;
import duke.tasks.Task;
import duke.tasks.TaskManager;
import duke.parser.Parser;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Includes the operations that can be performed on the file with fixed path /duke.txt, such as reading the
 * file when {@code Duke} is first started, and updating the file after every command cycle.
 */
public class Storage {

    private static final String STORAGE_PATH = "duke.txt";
    private static final String STORAGE_CREATED_MESSAGE =
            "I can't seem to find any file containing your past tasks, I'll create a new file for you!";
    private File storage = new File(STORAGE_PATH);

    private void createStorage() throws DukeException {
        try {
            storage.createNewFile();
        } catch (IOException exception) {
            throw new DukeException("IO Exception encountered: " + exception.getMessage());
        }
    }

    /**
     * Reads data strings from the storage and stores it in an {@code ArrayList}.
     *
     * @return list that represents the individual lines of data string in the storage
     * @throws DukeException if the storage with path /duke.txt does not exist and is first created, or if the
     * storage cannot be created due to IO problems.
     */
    private ArrayList<String> readFromStorage() throws DukeException {

        ArrayList<String> fileLines = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(storage);
            while (scanner.hasNext()) {
                fileLines.add(scanner.nextLine());
            }
        } catch (FileNotFoundException exception) {
            createStorage();
            throw new DukeException(STORAGE_CREATED_MESSAGE);
        }
        return fileLines;
    }

    /**
     * Converts the list data string read from storage to list of {@code Data}.
     *
     * @param fileLines list of data strings read from the storage
     * @return list of {@code Data}
     * @throws DukeException If the data string cannot be converted due to corrupted data
     */
    private ArrayList<Data> processStorageToData(ArrayList<String> fileLines) throws DukeException {
        ArrayList<Data> dataObjects = new ArrayList<>();
        Data dataObject;
        for (String fileLine : fileLines) {
            dataObject = Parser.parseData(fileLine);
            dataObjects.add(dataObject);
        }
        return dataObjects;
    }

    /**
     * Converts the individual {@code Data} in the list to {@code Task}, and then adds it to the {@code Task} list.
     *
     * @param dataObjects list of {@code Data}
     * @throws DukeException If the {@code Data} cannot be converted due to corrupted data
     */
    private void writeToTaskList(ArrayList<Data> dataObjects) throws DukeException {
        for (Data data : dataObjects) {
            Task task = data.toTask();
            TaskManager.addTask(task);
        }
    }

    /**
     * Includes the entire process of bringing the information of previously stored {@code Task} as data strings
     * from the storage to the current list of {@code Task} when {@code Duke} is first run.
     *
     * @throws DukeException Thrown if the data found in storage is corrupted, wiping the data in the file or
     * creating a new one in the process.
     */
    public void loadStorageToTaskList() throws DukeException {

        ArrayList<String> fileLines = readFromStorage();
        ArrayList<Data> dataObjects = processStorageToData(fileLines);
        writeToTaskList(dataObjects);
    }

    /**
     * Writes the list of data strings of the {@code Task} stored in the current list to the storage.
     *
     * @param fileLines list of data strings
     * @throws DukeException If there is an IO exception encountered
     */
    private void writeToStorage(ArrayList<String> fileLines) throws DukeException {
        try {
            FileWriter writer = new FileWriter(STORAGE_PATH);
            for (String line : fileLines) {
                writer.append(line);
            }
            writer.close();
        } catch (IOException exception) {
            throw new DukeException("IO Exception encountered: " + exception.getMessage());
        }
    }

    /**
     * Includes the entire process of bringing the {@code Task} objects in the current list to the storage
     * at the end of every command execution.
     *
     * @throws DukeException If there is an IO exception encountered in writing to the storage
     */
    public void loadTaskListToStorage() throws DukeException {
        ArrayList<String> fileLines = TaskManager.convertTasksToDataStringFormat();
        writeToStorage(fileLines);
    }
}
