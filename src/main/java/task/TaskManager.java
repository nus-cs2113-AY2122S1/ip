package task;

import main.Duke;
import parser.Parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class TaskManager {

    private static final String TODO_INDICATOR = "T";
    private static final String DEADLINE_INDICATOR = "D";
    private static final String EVENT_INDICATOR = "E";
    private static final String SEPARATOR = " | ";

    private final ArrayList<Task> tasks = new ArrayList<>();
    private File dataFile;

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public int getTaskCount() {
        return tasks.size();
    }


    /**
     * Adds a new task to the full list of tasks stored by taro based on whether it is a todo, deadline or event.
     * Increments the total count of tasks.
     *
     * @param taskName the description of the task to be added as specified by the user
     * @param date the date or time (either deadline or event date) to be attached to the task
     * @param taskType either "deadline" or "event", used to indicate whether the task is a deadline or event
     * @return the task.Task instance that has been added
     */
    public Task addTask(String taskType, String taskName, String date) throws IOException {
        Task newTask = null;
        switch(taskType) {
        case Parser.COMMAND_TODO:
            newTask = new Todo(taskName);
            tasks.add(newTask);
            writeAddTask(taskName);
            break;
        case Parser.COMMAND_DEADLINE:
            newTask = new Deadline(taskName, date);
            tasks.add(newTask);
            writeAddTask(DEADLINE_INDICATOR, taskName, date);
            break;
        case Parser.COMMAND_EVENT:
            newTask = new Event(taskName, date);
            tasks.add(newTask);
            writeAddTask(EVENT_INDICATOR, taskName, date);
            break;
        default:
            break;
        }
        return newTask;
    }

    /**
     * Marks the task at taskIndex in the list as done
     *
     * @param taskIndex the (index + 1) of the task to be marked done in tasks
     */
    public Task markAsDone(int taskIndex) throws IOException {
        Task doneTask = tasks.get(taskIndex);
        doneTask.setDone();
        writeDoneTask(taskIndex);
        return doneTask;
    }

    public Task deleteTask(int taskIndex) throws IOException {
        Task toDelete = tasks.get(taskIndex);
        tasks.remove(toDelete);
        writeDeleteTask(taskIndex);
        return toDelete;
    }

    public void loadFileContent(File dataFile) throws FileNotFoundException {
        Scanner scanner = new Scanner(dataFile);
        while (scanner.hasNext()) {
            String[] allItems = scanner.nextLine().trim().split("\\s*[|]\\s*", 0);
            switch (allItems[0]) {
            case TODO_INDICATOR:
                tasks.add(new Todo(allItems[2]));
                break;
            case DEADLINE_INDICATOR:
                tasks.add(new Deadline(allItems[2], allItems[3]));
                break;
            case EVENT_INDICATOR:
                tasks.add(new Event(allItems[2], allItems[3]));
                break;
            default:
                return;
            }
            if (Integer.parseInt(allItems[1]) == 1) {
                tasks.get(tasks.size() - 1).setDone();
            }
        }
        this.dataFile = dataFile;
    }

    private void writeAddTask(String taskDesc) throws IOException {
        FileWriter fw = new FileWriter(String.valueOf(Duke.DATA_FILE_PATH), true);
        fw.write("\n" + TaskManager.TODO_INDICATOR + SEPARATOR + "0" + SEPARATOR + taskDesc);
        fw.close();
    }

    private void writeAddTask(String taskType, String taskDesc, String date) throws IOException {
        FileWriter fw = new FileWriter(String.valueOf(Duke.DATA_FILE_PATH), true);
        fw.write("\n" + taskType + SEPARATOR + "0" + SEPARATOR + taskDesc + SEPARATOR + date);
        fw.close();
    }

    private void writeDoneTask(int taskIndex) throws IOException {
        Scanner scanner = new Scanner(dataFile);
        String newText = "";
        int indexScanner = 0;

        while (scanner.hasNext()) {
            if (indexScanner == taskIndex) {
                newText += scanner.nextLine().replace("0", "1") + System.lineSeparator();
            } else {
                newText += scanner.nextLine() + System.lineSeparator();
            }
            indexScanner++;
        }

        FileWriter fw = new FileWriter(String.valueOf(Duke.DATA_FILE_PATH), false);
        fw.write(newText);
        fw.close();
    }

    private void writeDeleteTask(int taskIndex) throws IOException {
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

        FileWriter fw = new FileWriter(String.valueOf(Duke.DATA_FILE_PATH), false);
        fw.write(newText);
        fw.close();
    }
}
