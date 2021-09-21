package kitty.io;

import kitty.Kitty;
import kitty.KittyException;
import kitty.task.Deadline;
import kitty.task.Event;
import kitty.task.Task;
import kitty.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

/**
 * The class <code>IO</code> includes methods that involves any reading/writing to external files.
 */
public class IO {
    public static final String DATA_PATH = "data/data.txt";

    public IO(String filePath) throws KittyException{
        initData(filePath);
    }

    /**
     * Reads from local data file and initialises data into tasks at hand.
     * @param filePath filePath is the path to the data file with respect to Content root.
     * @throws KittyException If the file is not found in filePath or the data provided is of the wrong format.
     */
    public static void initData(String filePath) throws KittyException {
        try {
            File f = new File(filePath);
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                String rawData = s.nextLine();
                String type = IOParser.getDataType(rawData);
                String status = IOParser.getDataStatus(rawData);
                String task = IOParser.getDataTask(rawData);

                // Add Tasks
                switch (type) {
                case "T":
                    addTodoFromRawData(status, task);
                    break;
                case "D":
                    addDeadlineFromRawData(status, task);
                    break;
                case "E":
                    addEventFromRawData(status, task);
                    break;
                default:
                    throw new KittyException("Invalid Raw Data!");
                }
            }
        } catch (FileNotFoundException e) {
            throw new KittyException("File not found!");
        }
    }

    /**
     * Adds a task of Event type from raw data provided in data file.
     * @param status status is the status of the completion of said task.
     * @param task task is the String provided by the data file, containing the task Description and task Date.
     */
    private static void addEventFromRawData(String status, String task) {
        String eventName = IOParser.getTaskName(task);
        LocalDate eventDate = IOParser.getTaskDate(task);
        Kitty.tasks.add(new Event(eventName, eventDate));
        if (IOParser.isTaskDone(status)) {
            Kitty.tasks.get(Kitty.tasks.size() - 1).setDone();
        }
    }

    /**
     * Adds a task of Deadline type from raw data provided in data file.
     * @param status status is the status of the completion of said task.
     * @param task task is the String provided by the data file, containing the task Description and task Date.
     */
    private static void addDeadlineFromRawData(String status, String task) {
        String deadlineName = IOParser.getTaskName(task);
        LocalDate deadlineDate = IOParser.getTaskDate(task);
        Kitty.tasks.add(new Deadline(deadlineName, deadlineDate));
        if (IOParser.isTaskDone(status)) {
            Kitty.tasks.get(Kitty.tasks.size() - 1).setDone();
        }
    }

    /**
     * Adds a task of Todo type from raw data provided in data file.
     * @param status status is the status of the completion of said task.
     * @param task task is the String provided by the data file, containing the task Description.
     */
    private static void addTodoFromRawData(String status, String task) {
        Kitty.tasks.add(new Todo(task));
        if (IOParser.isTaskDone(status)) {
            Kitty.tasks.get(Kitty.tasks.size() - 1).setDone();
        }
    }

    /**
     * Writes a new line to data file.
     * @param text text is the String in which we wish to write into data file.
     * @throws KittyException If text is of the wrong format.
     */
    public static void appendNewLine(String text) throws KittyException{
        try {
            FileWriter fw = new FileWriter(DATA_PATH, true);
            fw.write(text);
            fw.write(System.lineSeparator());
            fw.close();
        } catch (IOException e) {
            throw new KittyException("Invalid Input to Raw Data!");
        }
    }

    /**
     * Clears the data file of all data.
     * @throws KittyException If file is not found/
     */
    public static void clearFile() throws KittyException{
        try {
            FileWriter fw = new FileWriter(DATA_PATH);
            fw.close();
        } catch (IOException e) {
            throw new KittyException("File not found!");
        }
    }

    /**
     * Writes all tasks at hand onto data file.
     * @throws KittyException If the file is not found or an invalid formatting.
     */
    public static void updateData() throws KittyException{
        clearFile();
        for (Task task: Kitty.tasks) {
            if (task instanceof Todo) {
                if (task.isDone()) {
                    appendNewLine("T|1|" + task.getTaskName());
                } else {
                    appendNewLine("T|0|" + task.getTaskName());
                }
            } else if (task instanceof Deadline) {
                if (task.isDone()) {
                    appendNewLine("D|1|" + task.getTaskName() + "|" + ((Deadline) task).getDeadline());
                } else {
                    appendNewLine("D|0|" + task.getTaskName() + "|" + ((Deadline) task).getDeadline());
                }
            } else if (task instanceof Event) {
                if (task.isDone()) {
                    appendNewLine("E|1|" + task.getTaskName() + "|" + ((Event) task).getEventDate());
                } else {
                    appendNewLine("E|0|" + task.getTaskName() + "|" + ((Event) task).getEventDate());
                }
            }
        }
    }
}
