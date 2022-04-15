package duke.storage;

import duke.tasklist.TaskManager;
import duke.tasklist.task.Task;
import duke.ui.Parser;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class responsible for reading and writing into the storage file.
 */
public class FileManager {

    public String fileDirectory;
    public static String filePath;

    public static final int INDEX_TYPE = 0;
    public static final int INDEX_STATUS = 1;
    public static final int INDEX_DESCRIPTION = 2;
    public static final int INDEX_DATETIME = 3;

    /**
     * Creates FileManager object and initialises filePath and fileDirectory.
     * @param filePath String containing the file path of the storage file.
     */
    public FileManager(String filePath) {
        FileManager.filePath = filePath;
        String[] fileComponents = filePath.split("/");
        this.fileDirectory = fileComponents[0];
    }

    /**
     * Creates a new directory and file when application is used for the first time.
     * Scans through the storage file and loads all the tasks into the taskManager.
     * @param taskManager TaskManager object for task operations.
     * @param parser Parser object for parsing operations.
     * @throws IOException Error object when doing reading or writing operations in files.
     */
    public void load(TaskManager taskManager, Parser parser) throws IOException {
        File dataDirectory = new File(fileDirectory);
        File dataFile = new File(filePath);

        if (!dataDirectory.exists()) {
            dataDirectory.mkdir();
            dataFile.createNewFile();
            return;
        }

        Scanner fileScanner = new Scanner(dataFile);
        while (fileScanner.hasNext()) {
            String data = fileScanner.nextLine();
            String[] dataComponents = data.split("\\|");
            addSavedTask(taskManager, parser, dataComponents);
        }
    }

    /**
     * Creates and adds all types of tasks that were saved in the storage file into the taskManager
     * by constructing a user input corresponding to the saved task.
     * @param taskManager TaskManager object for task operations.
     * @param parser Parser object for parsing operations.
     * @param dataComponents Array of strings containing the details of the saved task.
     */
    public static void addSavedTask(TaskManager taskManager, Parser parser, String[] dataComponents) {
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
            taskManager.addSavedDeadline(parser, taskInfo, taskStatus);
            break;
        case "E":
            taskInfo = dataComponents[INDEX_DESCRIPTION] + " /at " + dataComponents[INDEX_DATETIME];
            taskManager.addSavedEvent(parser, taskInfo, taskStatus);
            break;
        default:
            break;
        }
    }

    /**
     * Updates the storage file to the latest task list.
     * @param tasks ArrayList containing all the tasks in the task list.
     * @throws IOException Error object when doing reading or writing operations in files.
     */
    public static void updateFile(ArrayList<Task> tasks) throws IOException {
        FileWriter fileWriter = new FileWriter(filePath);
        for (Task task : tasks) {
            String data = task.toDataFormat();
            fileWriter.write(data);
            fileWriter.write(System.lineSeparator());
        }
        fileWriter.close();
    }
}
