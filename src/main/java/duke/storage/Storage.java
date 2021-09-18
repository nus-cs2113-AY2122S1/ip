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

public class Storage {

    private static final String STORAGE_PATH = "duke.txt";
    private File storage = new File(STORAGE_PATH);
    private static final String STORAGE_CREATED_MESSAGE =
            "I can't seem to find any file containing your past tasks, I'll create a new file for you!";

    private void createStorage() throws DukeException {
        try {
            storage.createNewFile();
        } catch (IOException exception) {
            throw new DukeException("IO Exception encountered: " + exception.getMessage());
        }
    }

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

    private ArrayList<Data> processStorageToData(ArrayList<String> fileLines) throws DukeException {
        ArrayList<Data> dataObjects = new ArrayList<>();
        Data dataObject;
        for (String fileLine : fileLines) {
            dataObject = Parser.parseData(fileLine);
            dataObjects.add(dataObject);
        }
        return dataObjects;
    }

    private void writeToTaskList(ArrayList<Data> dataObjects) throws DukeException {
        for (Data data : dataObjects) {
            Task task = data.toTask();
            TaskManager.addTask(task);
        }
    }

    public void loadStorageToTaskList() throws DukeException {

        ArrayList<String> fileLines = readFromStorage();
        ArrayList<Data> dataObjects = processStorageToData(fileLines);
        writeToTaskList(dataObjects);
    }

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

    public void loadTaskListToStorage() throws DukeException {
        ArrayList<String> fileLines = TaskManager.convertTasksToDataStringFormat();
        writeToStorage(fileLines);
    }
}
