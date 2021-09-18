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

public class DataManager {

    private final String storagePath = "duke.txt";
    private final File storage = new File(storagePath);

    private static final Parser PARSER = new Parser();
    private static final TaskManager TASK_MANAGER = new TaskManager();

    private static final String STORAGE_CREATED_MESSAGE =
            "I can't seem to find any file containing your past tasks, I'll create a new file for you!";

    private void createStorage() throws DukeException {
        try {
            storage.createNewFile();
        } catch (IOException exception) {
            throw new DukeException("IO Exception encountered: " + exception.getMessage());
        }
    }

    public ArrayList<String> readFromStorage() throws DukeException {

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

    public ArrayList<Data> processStorage(ArrayList<String> fileLines) throws DukeException {
        ArrayList<Data> dataList = new ArrayList<>();
        Data data;
        for (String line : fileLines) {
            data = PARSER.parseData(line);
            dataList.add(data);
        }
        return dataList;
    }

    private void addDataToTaskList(ArrayList<Data> dataObjects) throws DukeException {
        for (Data data : dataObjects) {
            Task task = data.toTask();
            TASK_MANAGER.addTask(task);
        }
    }

    public void loadStorageToTaskList() throws DukeException {

        ArrayList<String> fileLines = readFromStorage();
        ArrayList<Data> dataObjects = processStorage(fileLines);
        addDataToTaskList(dataObjects);
    }

    public void writeToStorage(ArrayList<String> fileLines) throws DukeException {
        try {
            FileWriter writer = new FileWriter(storagePath);
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
