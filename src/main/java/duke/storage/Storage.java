package duke.storage;

import duke.common.Messages;
import duke.data.TaskList;
import duke.data.task.Deadline;
import duke.data.task.Event;
import duke.data.task.Task;
import duke.data.task.Todo;
import duke.ui.TextUi;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.util.Scanner;

/**
 * Represents the file used to store the task list data.
 */
public class Storage {

    public static final String INITIAL_TODO = "T";
    public static final String INITIAL_DEADLINE = "D";
    public static final String INITIAL_EVENT = "E";
    public static final String NUMBER_DONE = "1";
    public static final boolean IS_DONE_INITIAL = false;

    private TextUi ui;
    private TaskList tasks;

    private static final String FOLDER_PATH = "./data/";
    private static final String FILE_PATH = "data/tasks.txt";

    public Storage(TaskList tasks) {
        this.tasks = tasks;
        ui = new TextUi();
    }

    /**
     * Loads the {@code TaskList} data from the storage file and appends it to the TaskList.
     * If storage file does not exist, the program will attempt to make a new folder/file directory.
     * If storage file is corrupted, the program will exit and shows the user the corrupted line.
     */
    public void initTaskList() {
        try {
            appendFileContentsToArrayList();
        } catch (FileNotFoundException e) {
            File f = new File(FOLDER_PATH);
            if (f.mkdir()) {
                ui.showToUser(Messages.MESSAGE_INIT_NEW_STORAGE_FILE);
            } else {
                ui.showToUser(Messages.MESSAGE_INIT_FAILED);
            }
        } catch (IndexOutOfBoundsException e) {
            ui.showToUser("Data file corrupted! Exiting program...", "Corruption detected at line " + (tasks.getSize()+1));
            System.exit(0);
        }
    }

    /**
     * Decodes and appends the data from storage file to the TaskList.
     *
     * @throws FileNotFoundException if the data file does not exist
     * @throws IndexOutOfBoundsException if the data file is corrupt/ not in proper format
     */
    public void appendFileContentsToArrayList() throws FileNotFoundException, IndexOutOfBoundsException {
        File f = new File(FILE_PATH);
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String[] commandInput = s.nextLine().split(" \\| ", 4);
            String commandWord = commandInput[0];
            String isCompleteString = commandInput[1];
            String taskDescription = commandInput[2];
            String additionalDescription;
            if (commandWord.equals(INITIAL_TODO)) {
                additionalDescription = "";
            } else {
                additionalDescription = commandInput[3];
            }
            switch (commandWord) {
            case (INITIAL_TODO):
                tasks.addTask(new Todo(taskDescription));
                setTaskAsDone(isCompleteString);
                break;
            case (INITIAL_DEADLINE):
                tasks.addTask(new Deadline(taskDescription, additionalDescription));
                setTaskAsDone(isCompleteString);
                break;
            case (INITIAL_EVENT):
                tasks.addTask(new Event(taskDescription, additionalDescription));
                setTaskAsDone(isCompleteString);
                break;
            default:
                throw new IndexOutOfBoundsException();
            }
        }
    }

    /**
     * Retrieves the latest task and sets its isDone value as true.
     *
     * @param isCompleteString a String that represents "1" if the task is done and "0" if not
     */
    public void setTaskAsDone(String isCompleteString) {
        if (isCompleteString.equals(NUMBER_DONE)) {
            tasks.getTask(tasks.getSize() - 1).setDone();
        }
    }

    /**
     * Saves an Event task to the storage file.
     *
     * @param description the task description
     * @param at          the event date/time
     */
    public void appendEventToFile(String description, String at) {
        try {
            writeToFile(INITIAL_EVENT, description, at, IS_DONE_INITIAL);
        } catch (IOException e) {
            System.out.println("IO error!");
        }
    }

    /**
     * Saves a Deadline task to the storage file.
     *
     * @param description the task description
     * @param by          the deadline
     */
    public void appendDeadlineToFile(String description, String by) {
        try {
            writeToFile(INITIAL_DEADLINE, description, by, IS_DONE_INITIAL);
        } catch (IOException e) {
            System.out.println("IO error!");
        }
    }

    /**
     * Saves a Todo task to the storage file.
     *
     * @param todoInput the task description
     */
    public void appendTodoToFile(String todoInput) {
        try {
            writeToFile(INITIAL_TODO, todoInput, "", IS_DONE_INITIAL);
        } catch (IOException e) {
            System.out.println("IO error!");
        }
    }

    /**
     * Writes the provided data to the storage file.
     *
     * @param taskInstance   the initial of the task provided
     * @param rawText        the task description
     * @param additionalText the deadline/ event datetime of the task
     * @param isDone         the complete status of the task
     * @throws IOException if an IO error is detected
     */
    public void writeToFile(String taskInstance, String rawText, String additionalText, boolean isDone) throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH, true);
        String additionalTextWithBorders = (additionalText.equals("") ? "" : " | " + additionalText);
        String taskAsText = taskInstance + " | " + isDoneString(isDone) + " | " + rawText + additionalTextWithBorders + System.lineSeparator();
        fw.write(taskAsText);
        fw.close();
    }

    /**
     * Utility method for converting the isDone boolean into a String format.
     *
     * @param isDone the completed status of the task
     * @return "1" if isDone is true and "0" if isDone is false
     */
    public static String isDoneString(boolean isDone) {
        if (isDone) {
            return "1";
        } else {
            return "0";
        }
    }

    /**
     * Clears the storage file.
     *
     * @throws IOException if IO error is detected
     */
    public void clearFile() throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH);
        fw.write("");
        fw.close();
    }

    /**
     * Overwrites the entire storage file with new data from {@code TaskList}
     */
    public void OverwriteListToFile() {
        try {
            clearFile();
            for (Task task : tasks.getList()) {
                if (task instanceof Todo) {
                    writeToFile(INITIAL_TODO, task.getDescription(), "", task.isDone());
                } else if (task instanceof Deadline) {
                    Deadline deadlineTask = (Deadline) task;
                    writeToFile(INITIAL_DEADLINE, deadlineTask.getDescription(), deadlineTask.getBy(), deadlineTask.isDone());
                } else if (task instanceof Event) {
                    Event eventTask = (Event) task;
                    writeToFile(INITIAL_EVENT, eventTask.getDescription(), eventTask.getAt(), eventTask.isDone());
                }
            }
        } catch (IOException e) {
            System.out.println("IO Error!");
        }
    }
}
