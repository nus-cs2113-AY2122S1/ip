package storage;

import parser.Parser;
import task.TaskList;
import ui.UI;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Storage {
    private static final String ROOT_DIR = System.getProperty("user.dir");
    private static final String DATA_DIR = "data";
    private static final String DATA_FILE = "taro.txt";
    public static final Path DATA_FILE_PATH = Paths.get(ROOT_DIR, "src", DATA_DIR, DATA_FILE);
    private static final String SEPARATOR = " | ";
    private static final String TODO_INDICATOR = "T";
    private static final String DEADLINE_INDICATOR = "D";
    private static final String EVENT_INDICATOR = "E";
    private static final String INCOMPLETE_TASK_STATUS = "0";
    private static final String COMPLETE_TASK_STATUS = "1";

    private File dataFile;

    /**
     * Either loads the saved data from java/data/taro.txt or creates java/data/taro.txt to store the data generated
     * by the user while operating taro.
     *
     * @param taskList the task manager used to handle modifications to the internal storage of Task objects by taro
     * @param ui the UI object that handles i/o for taro
     */
    public void getDataFile(TaskList taskList, UI ui) {
        try {
            dataFile = new File(String.valueOf(DATA_FILE_PATH));
            if(dataFile.getParentFile().mkdirs()) {
                ui.printString("a new directory \"data\" was created to save your data");
            }
            if (dataFile.createNewFile()) {
                ui.printString("a new file \"data/taro.txt\" was created to save your data");
            } else {
                try {
                    loadFileContent(taskList);
                    ui.printString("your saved data has been loaded");
                } catch (FileNotFoundException e) {
                    ui.printString("there was an error retrieving your saved data");
                }
            }
        } catch (IOException e) {
            ui.printString("there was an error retrieving your saved data");
        }
    }

    /**
     * Initialises the list of tasks stored by taro to reflect the stored tasks in java/data/taro.txt by parsing the
     * saved file and adding the relevant tasks to the internal list of tasks stored by taro.
     *
     * @param taskList the task manager used to handle modifications to the internal storage of Task objects by taro
     * @throws FileNotFoundException the exception that occurs when the Scanner object cannot find the data file
     * saved by taro
     */
    private void loadFileContent(TaskList taskList) throws FileNotFoundException {
        Scanner scanner = new Scanner(dataFile);
        while (scanner.hasNext()) {
            String[] allItems = scanner.nextLine().trim().split("\\s*[|]\\s*", 0);
            switch (allItems[0]) {
            case TODO_INDICATOR:
                taskList.addTask(Parser.COMMAND_TODO, allItems[2], "");
                break;
            case DEADLINE_INDICATOR:
                taskList.addTask(Parser.COMMAND_DEADLINE, allItems[2], allItems[3]);
                break;
            case EVENT_INDICATOR:
                taskList.addTask(Parser.COMMAND_EVENT, allItems[2], allItems[3]);
                break;
            default:
                return;
            }
            if (Integer.parseInt(allItems[1]) == 1) {
                int taskIndex = taskList.getTasks().size() - 1;
                taskList.markAsDone(taskIndex);
            }
        }
    }

    /**
     * Writes a new Todo task to the java/data/taro.txt file
     *
     * @param taskDesc the description of the task to be added
     * @throws IOException the exception thrown when the FileWriter object is unable to find the saved data file
     */
    public void writeAddTask(String taskDesc) throws IOException {
        FileWriter fw = new FileWriter(String.valueOf(DATA_FILE_PATH), true);
        fw.write(TODO_INDICATOR + SEPARATOR + "0" + SEPARATOR + taskDesc + "\n");
        fw.close();
    }

    /**
     * Writes a new Event or Deadline task to the java/data/taro.txt file
     *
     * @param taskType either "deadline" or "event"
     * @param taskDesc the description of the task to be added
     * @param date either the /at date (for events) or the /by date (for deadlines)
     * @throws IOException the exception thrown when the FileWriter object is unable to find the saved data file
     */
    public void writeAddTask(String taskType, String taskDesc, String date) throws IOException {
        FileWriter fw = new FileWriter(String.valueOf(DATA_FILE_PATH), true);
        String taskTypeIndicator = "";
        if (taskType.equals(Parser.COMMAND_DEADLINE)) {
            taskTypeIndicator = DEADLINE_INDICATOR;
        } else if (taskType.equals(Parser.COMMAND_EVENT)) {
            taskTypeIndicator = EVENT_INDICATOR;
        }
        fw.write(taskTypeIndicator + SEPARATOR + INCOMPLETE_TASK_STATUS + SEPARATOR + taskDesc + SEPARATOR + date +
                        "\n");
        fw.close();
    }

    public void writeDoneTask(int taskIndex) throws IOException {
        Scanner scanner = new Scanner(dataFile);
        String newText = "";
        int indexScanner = 0;

        while (scanner.hasNext()) {
            if (indexScanner == taskIndex) {
                newText += scanner.nextLine().replace(INCOMPLETE_TASK_STATUS, COMPLETE_TASK_STATUS) + System.lineSeparator();
            } else {
                newText += scanner.nextLine() + System.lineSeparator();
            }
            indexScanner++;
        }

        FileWriter fw = new FileWriter(String.valueOf(DATA_FILE_PATH), false);
        fw.write(newText);
        fw.close();
    }

    public void writeDeleteTask(int taskIndex) throws IOException {
        Scanner scanner = new Scanner(dataFile);
        String newText = "";
        int indexScanner = 0;

        while (scanner.hasNext()) {
            if (indexScanner == taskIndex) {
                scanner.nextLine();
            } else {
                newText += scanner.nextLine() + System.lineSeparator();
            }
            indexScanner++;
        }

        FileWriter fw = new FileWriter(String.valueOf(DATA_FILE_PATH), false);
        fw.write(newText);
        fw.close();
    }

}
