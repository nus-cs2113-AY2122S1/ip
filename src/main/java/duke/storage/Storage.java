package duke.storage;

import duke.Duke;
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

public class Storage {

    public static final String INITIAL_TODO = "T";
    public static final String INITIAL_DEADLINE = "D";
    public static final String INITIAL_EVENT = "E";
    public static final boolean IS_DONE_INITIAL = false;

    private TextUi ui;
    private TaskList tasks;

    private static final String FOLDER_PATH = "./data/";
    private static final String FILE_PATH = "data/tasks.txt";

    public Storage(TaskList tasks) {
        this.tasks = tasks;
        ui = new TextUi();
    }

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
        }
    }

    public void appendFileContentsToArrayList() throws FileNotFoundException {
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
            }
        }
    }

    public void writeToFile(String taskInstance, String rawText, String additionalText, boolean isDone) throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH, true);
        String additionalTextWithBorders = (additionalText.equals("") ? "" : " | " + additionalText);
        String taskAsText = taskInstance + " | " + isDoneString(isDone) + " | " + rawText + additionalTextWithBorders + System.lineSeparator();
        fw.write(taskAsText);
        fw.close();
    }

    public static String isDoneString(boolean isDone) {
        if (isDone) {
            return "1";
        } else {
            return "0";
        }
    }

    public void appendEventToFile(String description, String at) {
        try {
            writeToFile(INITIAL_EVENT, description, at, IS_DONE_INITIAL);
        } catch (IOException e) {
            System.out.println("IO error!");
        }
    }

    public void appendDeadlineToFile(String description, String by) {
        try {
            writeToFile(INITIAL_DEADLINE, description, by, IS_DONE_INITIAL);
        } catch (IOException e) {
            System.out.println("IO error!");
        }
    }

    public void appendTodoToFile(String todoInput) {
        try {
            writeToFile(INITIAL_TODO, todoInput, "", IS_DONE_INITIAL);
        } catch (IOException e) {
            System.out.println("IO error!");
        }
    }

    public void clearFile() throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH);
        fw.write("");
        fw.close();
    }

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

    public void setTaskAsDone(String isCompleteString) {
        if (isCompleteString.equals(Duke.NUMBER_DONE)) {
            tasks.getTask(tasks.getSize() - 1).setDone();
        }
    }

}
