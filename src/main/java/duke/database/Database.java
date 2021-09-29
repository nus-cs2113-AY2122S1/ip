package duke.database;

import duke.tasks.TaskList;
import duke.logic.Logic;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

/**
 * Database class gets the filepath from Logic class and handles the saving of data and uploading
 * of data to Logic class
 *
 * @param "filepath" filepath of storage file, duke.txt.
 */
public class Database{
    private static String filePath;

    /**
     * Constructor
     *
     * @param filePath file path of storage file
     */
    public Database(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads task from duke.txt data file if it exists. If the file does
     * not exist, the file is created.
     *
     * @return tasksCopy previous state of tasks if the data file exists,
     * or an empty TaskList if the data file does not exist.
     */
    public static TaskList loadTasks() {
        TaskList tasksCopy = new TaskList();

        File storedFile;
        try {
            storedFile = new File(filePath);
            //if savedFile is not present and a new file is created
            if (storedFile.createNewFile()) {
                return tasksCopy;
            } else {
                Scanner readFile = new Scanner(storedFile);
                while (readFile.hasNext()) {
                    Logic.listIndex++;
                    String fileLine = readFile.nextLine();
                    Scanner lineData = new Scanner(fileLine);
                    lineData.useDelimiter("\\|");
                    String commandType = lineData.next().trim();
                    String doneStatus = lineData.next().trim();
                    String taskDescription = lineData.next().trim();

                    switch (commandType) {
                    case "T":
                        loadTodoTask(taskDescription, doneStatus, tasksCopy);
                        break;
                    case "D":
                        loadDeadlineTask(lineData, taskDescription, doneStatus, tasksCopy);
                        break;
                    case "E":
                        loadEventTask(lineData, taskDescription, doneStatus, tasksCopy);
                        break;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Oops! An error cropped up while loading file!");
        }
        return tasksCopy;
    }

    /**
     * loads a Todo task from file into program list.
     *
     * @param taskDescription the description of the task in the file
     * @param doneStatus a string, either 1 or 0, denoting whether the task is done(1 means done)
     * @param tasksCopy TaskList to store restored tasks
     */
    public static void loadTodoTask(String taskDescription, String doneStatus, TaskList tasksCopy) {
        Todo savedTodo = new Todo(taskDescription);
        if (doneStatus.equals("1")) {
            savedTodo.setDone();
        }
        tasksCopy.addTask(savedTodo);
    }

    /**
     * loads a Deadline task from file into program list.
     *
     * @param  lineData a Scanner
     * @param taskDescription the description of the task in the file
     * @param doneStatus a string, either 1 or 0, denoting whether the task is done(1 means done)
     * @param tasksCopy TaskList to store restored tasks
     */
    public static void loadDeadlineTask(Scanner lineData, String taskDescription, String doneStatus, TaskList tasksCopy) {
        String deadLineDate = lineData.next().trim();
        LocalDate deadLineDateFormatted = LocalDate.parse(deadLineDate);
        Deadline savedDeadLine = new Deadline(taskDescription, deadLineDateFormatted);
        if (doneStatus.equals("1")) {
            savedDeadLine.setDone();
        }
        tasksCopy.addTask(savedDeadLine);
    }

    /**
     * loads an Event task from file into program list.
     *
     * @param  lineData a Scanner
     * @param taskDescription the description of the task in the file
     * @param doneStatus a string, either 1 or 0, denoting whether the task is done(1 means done)
     * @param tasksCopy TaskList to store restored tasks
     */
    public static void loadEventTask(Scanner lineData, String taskDescription, String doneStatus, TaskList tasksCopy) {
        String eventDate = lineData.next().trim();
        LocalDate eventDateFormatted = LocalDate.parse(eventDate);
        Event savedEvent = new Event(taskDescription, eventDateFormatted);
        if (doneStatus.equals("1")) {
            savedEvent.setDone();
        }
        tasksCopy.addTask(savedEvent);
    }

    /**
     * Saves the entire taskList into data storage file.
     *
     * @param "taskList" the current list of tasks
     */
    public static void saveFile(TaskList taskList) {
        try {
            File database;
            database = new File("duke.txt");
            database.createNewFile();
        } catch (IOException e) {
            System.out.println("Oops! An error cropped up while saving");
        }
        String savedData = "";
        for (int i = 1; i <= taskList.getSize(); i++) {
            Task currTask = taskList.getTask(i);
            savedData += currTask.toStringStore();
            savedData += "\n";
        }
        try {
            FileWriter editor = new FileWriter("duke.txt");
            editor.write(savedData);
            editor.close();
        } catch (IOException e) {
            System.out.println("Oops! An error occurred during saving.");
            e.printStackTrace();
        }
    }

    /**
     * Updates the data storage file by appending the most
     * recently added file to the end of the file.
     *
     * @param taskList the current list of tasks
     */
    public static void autoSaveFile(TaskList taskList) {
        Task currTask = taskList.getTask(taskList.getSize());
        String data = currTask.toStringStore() + "\n";
        try {
            FileWriter editor = new FileWriter("duke.txt", true);
            editor.write(data);
            editor.close();
        } catch (IOException e) {
            System.out.println("Oops! An error occurred during autosave.");
            e.printStackTrace();
        }
    }
}
