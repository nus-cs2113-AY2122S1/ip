package duke.command;

import duke.task.Task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileManager {

    public static final String FILE_DIRECTORY = "data";
    public static final String FILE_PATH = "data/savedTasks.txt";

    public static final int INDEX_TYPE = 0;
    public static final int INDEX_STATUS = 1;
    public static final int INDEX_DESCRIPTION = 2;
    public static final int INDEX_DATETIME = 3;

    public static void parseFile(TaskManager taskManager) throws IOException{
        File dataDirectory = new File(FILE_DIRECTORY);
        File dataFile = new File(FILE_PATH);

        if (!dataDirectory.exists()) {
            dataDirectory.mkdir();
            dataFile.createNewFile();
            return;
        }

        Scanner fileScanner = new Scanner(dataFile);
        while (fileScanner.hasNext()) {
            String data = fileScanner.nextLine();
            String[] dataComponents = data.split("\\|");
            addSavedTask(taskManager, dataComponents);
        }
    }

    public static void addSavedTask(TaskManager taskManager, String[] dataComponents) {
        String dataTaskType = dataComponents[INDEX_TYPE];
        String taskInfo;
        boolean taskStatus = dataComponents[INDEX_STATUS].trim().equals("1");

        int i = 0;
        for (String dataComponent : dataComponents) {
            dataComponents[i] = dataComponent.trim();
            i++;
        }
        switch (dataTaskType.trim()) {
        case "T":
            taskInfo = dataComponents[INDEX_DESCRIPTION];
            taskManager.addSavedToDo(taskInfo, taskStatus);
            break;
        case "D":
            taskInfo = dataComponents[INDEX_DESCRIPTION] + " /by " + dataComponents[INDEX_DATETIME];
            taskManager.addSavedDeadline(taskInfo, taskStatus);
            break;
        case "E":
            taskInfo = dataComponents[INDEX_DESCRIPTION] + " /at " + dataComponents[INDEX_DATETIME];
            taskManager.addSavedEvent(taskInfo, taskStatus);
            break;
        default:
            break;
        }
    }

    public static void updateFile(ArrayList<Task> tasks) throws IOException {
        FileWriter fileWriter = new FileWriter(FILE_PATH);
        for (Task task : tasks) {
            String data = task.toDataFormat();
            fileWriter.write(data);
            fileWriter.write(System.lineSeparator());
        }
        fileWriter.close();
    }

    public static void loadData(TaskManager taskManager) {
        try {
            parseFile(taskManager);
        } catch (IOException e) {
            System.out.println("Error while trying to load data file");
        }
    }

    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();
        loadData(taskManager);

    }
}

