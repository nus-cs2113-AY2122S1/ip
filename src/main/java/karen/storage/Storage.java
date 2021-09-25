package karen.storage;

import karen.parser.Parser;
import karen.tasklist.TaskList;
import karen.tasklist.task.Task;
import karen.ui.Ui;

import java.io.File;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public abstract class Storage {
    private static final String FILE_PATH = "data/bobby.txt";

    /**
     * This method is called when there is no existing storage file in the specific file path,
     * FILE_PATH. It uses the File object to create a new storage file to the specified file path.
     * This method also catches any exceptions thrown when creating a new file.
     *
     * @param dataFile file object with a specified path name, FILE_PATH
     */
    public static void createFile(File dataFile) {
        try {
            if (!dataFile.getParentFile().exists()) {
                dataFile.getParentFile().mkdirs();
            }
            dataFile.createNewFile();
        } catch (IOException e) {
            Ui.printCreateFileErrorMessage();
        }
    }

    public static String getFilePath() {
        return FILE_PATH;
    }

    /**
     * This method is used to load data into the TaskList object when the program starts
     * running. It checks for any existing storage file located in FILE_PATH, and creates
     * a new storage file if there is no existing file. If there is already a storage file
     * located in FILE_PATH, this method will parse the data in the storage file into
     * the TaskList object.
     *
     * @param taskList TaskList object used to manage the Task objects in its taskList
     * @param parser Parser object to parse data from the file into Task objects
     */
    public static void bootUpData(TaskList taskList, Parser parser) {
        File dataFile = new File(FILE_PATH);
        try {
            if (!dataFile.exists()) {
                createFile(dataFile);
            }
            //parse saved data into taskList
            readFile(taskList, dataFile, parser);
        } catch (IOException e) {
            Ui.printIOExceptionMessage();
        }
    }

    /**
     * This method is used to add Task objects parsed, using the Parser object,
     * from the data file into the TaskList object.
     *
     * @param taskList TaskList object used for adding Task objects into its taskList
     * @param dataFile File object with specified path name used for accessing of data in the path
     * @param parser Parser object to parse the data accessed in the file path into Task objects
     * @throws IOException if there are errors when reading the data in the specified file path
     */
    public static void readFile(TaskList taskList, File dataFile, Parser parser) throws IOException{
        Scanner s = new Scanner(dataFile);
        while (s.hasNext()) {
            String fileData = s.nextLine();
            Task task = parser.parseFileData(fileData);
            taskList.addTask(task);
        }
    }

    /**
     * This method is used for saving all Task objects found in taskList as data in the storage file,
     * located in the specified file path.
     *
     * @param taskList TaskList object to manage Task objects
     * @throws IOException if there are errors when writing data into the specified file path
     */
    public static void writeToFile(TaskList taskList) throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH);
        int totalTasks = taskList.getTaskList().size();
        String textToAdd = "";
        for (int i = 0; i < totalTasks; i ++){
            Task currentTask = taskList.getTaskList().get(i);
            textToAdd += currentTask.getFormattedFileDescription();
            textToAdd += "\n";
        }
        fw.write(textToAdd);
        fw.close();
    }
}
