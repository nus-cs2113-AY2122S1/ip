package storage;

import common.DukeException;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

import java.io.File;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * Class that allows for loading and saving of files.
 */
public class Storage {
    public static final String ERROR_MESSAGE_UNKNOWN_TASK = "Unknown task encountered. Skipping.";
    public static final char DEADLINE_SYMBOL = 'D';
    public static final char EVENT_SYMBOL = 'E';
    public static final char TODO_SYMBOL = 'T';

    private static File dataFile;

    public File getDataFile() {
        return dataFile;
    }

    public Storage(String filePath) {
        dataFile = new File(filePath);
    }

    /**
     * Loads the saved task list from the file if it exists.
     * Else, create new file.
     *
     * @return saved task list
     * @throws DukeException if file is not found or there is an error writing to the file.
     */
    public ArrayList<Task> load() throws DukeException {
        if (!dataFile.exists()) {
            createFile();
        }
        ArrayList<Task> taskList = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(dataFile));
            String line = reader.readLine();
            while (line != null) {
                Task t = convertFileStringToTask(line);
                taskList.add(t);
                line = reader.readLine();
            }
            reader.close();
        } catch (FileNotFoundException f) {
            createFile();
            throw new DukeException("File not found. Creating new file. ");
        } catch (IOException e) {
            throw new DukeException("Error writing to file: " + getDataFile().toPath());
        }
        return taskList;
    }

    /**
     * Saves the task list after every modification to the task list.
     *
     * @param taskList the task list
     * @throws DukeException if there is error saving to file.
     */
    public void save(ArrayList<Task> taskList) throws DukeException {
        if (!dataFile.exists()) {
            createFile();
        }
        try {
            FileWriter fw = new FileWriter(dataFile, false);
            BufferedWriter bw = new BufferedWriter(fw);

            for (Task task : taskList) {
                bw.write(task.toFileString());
                bw.newLine();
            }
            bw.close();
        } catch (IOException e) {
            throw new DukeException("Error saving to file. ");
        }
    }

    private static void createFile() throws DukeException {
        if (dataFile.exists()) {
            System.out.println("File exists. ");
            return;
        }
        try {
            if (!dataFile.getParentFile().exists()) {
                dataFile.getParentFile().mkdirs();
            }
            dataFile.createNewFile();
        } catch (IOException e) {
            throw new DukeException("Error. Could not create file. ");
        }
    }

    private static char getTaskType(String arg) throws DukeException{
        if (arg.isEmpty()) {
            throw new DukeException("Error. Empty task type");
        }
        return arg.charAt(0);
    }

    private static boolean isCompletedTask(String arg) throws NumberFormatException, DukeException {
        if (arg.isEmpty()) {
            throw new DukeException("Error. Empty task status.");
        }
        int isCompleted = Integer.parseInt(arg);
        if (isCompleted != 0 && isCompleted != 1) {
            throw new DukeException("Error. Unknown task status.");
        }
        return isCompleted == 1;
    }

    private static String getTaskDescription(String arg) throws DukeException {
        if (arg.isEmpty()) {
            throw new DukeException("Error. Empty task description.");
        }
        return arg;
    }

    private static LocalDateTime getDateTime(String arg) throws DukeException, DateTimeParseException {
        if (arg.isEmpty()) {
            throw new DukeException("Error. Empty date time");
        }
        return LocalDateTime.parse(arg);
    }

    private static Task convertFileStringToTask(String line) throws DukeException {
        try {
            String[] args = line.trim().split("\\|");
            char taskType = getTaskType(args[0].trim());
            boolean isDone = isCompletedTask(args[1].trim());
            String description = getTaskDescription(args[2].trim());
            LocalDateTime dateTime;

            switch (taskType) {
            case TODO_SYMBOL:
                return new Todo(description, isDone);
            case DEADLINE_SYMBOL:
                dateTime = getDateTime(args[3].trim());
                return new Deadline(description, isDone, dateTime);
            case EVENT_SYMBOL:
                dateTime = getDateTime(args[3].trim());
                return new Event(description, isDone, dateTime);
            default:
                throw new DukeException(ERROR_MESSAGE_UNKNOWN_TASK);
            }
        } catch (NumberFormatException | IndexOutOfBoundsException | DateTimeParseException | DukeException e) {
            throw new DukeException(ERROR_MESSAGE_UNKNOWN_TASK);
        }
    }
}



