package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class that deals with loading and saving tasks in an external file.
 */
public class Storage {

    protected static String filePath;

    private static final String DATE_TIME_FORMAT = "dd-MM-yyyy HH:mm";

    /**
     * Storage class constructor.
     *
     * @param filePath File path of external file.
     */
    public Storage(String filePath) {
        Storage.filePath = filePath;
    }

    /**
     * Creates a new file in the file path if the file does not exist.
     */
    public void createFile() {
        File f = new File(filePath);
        try {
            f.getParentFile().mkdirs();
            if (!f.exists()) {
                f.createNewFile();
            }
        } catch (IOException e) {
            System.out.println(" I/O ERROR ");
        }
    }

    /**
     * Loads in data from an external file, makes sense of them, store them in a task list
     * and return the task list.
     *
     * @return task list the data are stored in.
     * @throws DukeException If file is not found.
     */
    public TaskList load() throws DukeException {
        createFile();
        TaskList taskList = new TaskList();
        try {
            FileInputStream fileInputStream = new FileInputStream(filePath);
            Scanner s = new Scanner(fileInputStream);
            while (s.hasNext()) {
                String line = s.nextLine();

                if (line.startsWith("T")) {
                    String taskDisplay = line.substring(8);
                    taskList.addToStringList(taskDisplay);
                    Todo todoTask = new Todo(taskDisplay, 'T');
                    taskList.addToTaskList(todoTask);
                    taskList.addToDueDateList(null);
                    taskList.addToFormattedDueDateList(null);

                    if (line.charAt(4) == '1') {
                        taskList.markAsCompleted(taskList.getListCount() - 1);
                    }
                } else if (line.startsWith("D")) {
                    int deadlineIndex = line.indexOf("/");
                    String taskDisplay = line.substring(8, deadlineIndex);
                    taskList.addToStringList(taskDisplay);
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);
                    LocalDateTime formatDateTime = LocalDateTime.parse(line.substring(deadlineIndex + 1), formatter);
                    String formattedDateTime = formatDateTime.format(DateTimeFormatter
                            .ofLocalizedDateTime(FormatStyle.MEDIUM));
                    String doBy = "(" + formattedDateTime + ")";
                    Deadline deadlineTask = new Deadline(taskDisplay, 'D', doBy);
                    taskList.addToTaskList(deadlineTask);
                    taskList.addToDueDateList(line.substring(deadlineIndex + 1));
                    taskList.addToFormattedDueDateList(formattedDateTime);

                    if (line.charAt(4) == '1') {
                        taskList.markAsCompleted(taskList.getListCount() - 1);
                    }
                } else if (line.startsWith("E")) {
                    int eventIndex = line.indexOf("/");
                    String taskDisplay = line.substring(8, eventIndex);
                    taskList.addToStringList(taskDisplay);
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);
                    LocalDateTime formatDateTime = LocalDateTime.parse(line.substring(eventIndex + 1), formatter);
                    String formattedDateTime = formatDateTime.format(DateTimeFormatter
                            .ofLocalizedDateTime(FormatStyle.MEDIUM));
                    String doBy = "(" + formattedDateTime + ")";
                    Event eventTask = new Event(taskDisplay, 'E', doBy);
                    taskList.addToTaskList(eventTask);
                    taskList.addToDueDateList(line.substring(eventIndex + 1));
                    taskList.addToFormattedDueDateList(formattedDateTime);

                    if (line.charAt(4) == '1') {
                        taskList.markAsCompleted(taskList.getListCount() - 1);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(" FILE NOT FOUND ");
        }
        return taskList;
    }

    /**
     * Loads data into a new external file of the specific file path.
     *
     * @param filePath  File path of external file.
     * @param textToAdd Data to load into the file
     * @throws IOException If file is not found.
     */
    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    /**
     * Adds data into an existing external file of the specific file path
     *
     * @param filePath     File path of external file.
     * @param textToAppend Data to add into the file.
     * @throws IOException If file is not found.
     */
    private static void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
        fw.write(textToAppend);
        fw.close();
    }

    /**
     * Formats and loads data into an external file.
     *
     * @param taskList    List containing tasks with the due date and time.
     * @param stringList  List containing details of tasks only
     * @param dueDateList List containing due date and time only.
     */
    public static void writeData(TaskList taskList, ArrayList<String> stringList, ArrayList<String> dueDateList) {
        try {
            if (taskList.getTask(0).getTaskType() == 'T') {
                String firstData = taskList.getTask(0).getTaskType() + " | "
                        + taskList.getTask(0).getWrittenIcon() + " | " + stringList.get(0);
                writeToFile(filePath, firstData + System.lineSeparator());
            } else {
                String firstData = taskList.getTask(0).getTaskType() + " | "
                        + taskList.getTask(0).getWrittenIcon() + " | " + stringList.get(0)
                        + "/" + dueDateList.get(0);
                writeToFile(filePath, firstData + System.lineSeparator());
            }
            for (int i = 1; i < taskList.getListCount(); i++) {
                if (taskList.getTask(i).getTaskType() == 'T') {
                    String data = "T | " + taskList.getTask(i).getWrittenIcon() + " | " + stringList.get(i);
                    appendToFile(filePath, data + System.lineSeparator());
                } else if (taskList.getTask(i).getTaskType() == 'D') {
                    String data = "D | " + taskList.getTask(i).getWrittenIcon() + " | "
                            + stringList.get(i) + "/" + dueDateList.get(i);
                    appendToFile(filePath, data + System.lineSeparator());
                } else if (taskList.getTask(i).getTaskType() == 'E') {
                    String data = "E | " + taskList.getTask(i).getWrittenIcon() + " | "
                            + stringList.get(i) + "/" + dueDateList.get(i);
                    appendToFile(filePath, data + System.lineSeparator());
                }
            }
        } catch (IOException e) {
            System.out.println(" I/O ERROR ");
        }
    }
}
