package duke;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import duke.exception.DukeException;
import duke.exception.ExceptionMessages;
import duke.task.*;


public class FileManager {
    private static final String ROOT = System.getProperty("user.dir");
    private static final Path FILE_PATH = Paths.get(ROOT, "data", "duke.txt");
    private static final Path DIRECTORY_PATH = Paths.get(ROOT, "data");

    public static final String ERROR_WRITING_TO_SAVE_FILE = "Error writing to save file";
    public static final String ERROR_CREATING_SAVE_FILE = "Error creating save file";
    public static final String ERROR_CREATING_DATA_DIRECTORY = "Error creating data directory";
    public static final String ERROR_DUKE_UNKNOWN = "Unknown error with Duke occurred";
    public static final String ERROR_CREATING_SAVE_FILE1 = "Error creating save file";
    public static final String ERROR_READING_SAVE_FILE = "Error reading save file, some tasks may have been lost";
    public static final String ERROR_CONVERTING_SAVE_FILE = "Error converting file to task list, some tasks may have been lost";


    public static void writeTaskListToFile(TaskList taskList) {
        try {
            if (!Files.exists((DIRECTORY_PATH))) {
                createDataDirectory();
            }
            if (!Files.exists(FILE_PATH)) {
                createFile();
            }
            FileWriter fw = new FileWriter(FILE_PATH.toString());
            fw.write(taskList.toFile());
            fw.close();
        } catch (IOException e) {
            PrintUtils.printErrorMessage(ERROR_WRITING_TO_SAVE_FILE);
        } catch (DukeException e) {
            switch (e.getMessage()) {
            case ExceptionMessages.EXCEPTION_CREATE_FILE_FAIL:
                PrintUtils.printErrorMessage(ERROR_CREATING_SAVE_FILE);
                break;
            case ExceptionMessages.EXCEPTION_CREATE_DIRECTORY_FAIL:
                PrintUtils.printErrorMessage(ERROR_CREATING_DATA_DIRECTORY);
                break;
            default:
                PrintUtils.printErrorMessage(ERROR_DUKE_UNKNOWN);
            }

        }
    }

    private static void createDataDirectory() throws DukeException {
        File newDirectory = new File(DIRECTORY_PATH.toString());
        boolean createSuccess = newDirectory.mkdir();
        if (!createSuccess) {
            throw new DukeException(ExceptionMessages.EXCEPTION_CREATE_DIRECTORY_FAIL);
        }
    }


    private static void createFile() throws DukeException {
        try {
            File newFile = new File(FILE_PATH.toString());
            boolean createSuccess = newFile.createNewFile();
            if (!createSuccess) {
                throw new DukeException(ExceptionMessages.EXCEPTION_CREATE_FILE_FAIL);
            }
        } catch (IOException e) {
            PrintUtils.printErrorMessage(ERROR_CREATING_SAVE_FILE1);
        }
    }

    public static TaskList loadTaskListFromFile() {
        if (!Files.exists(FILE_PATH)) {
            return new TaskList();
        }
        TaskList tasks = new TaskList();
        try {
            File newFile = new File(FILE_PATH.toString());
            Scanner s = new Scanner(newFile);
            while (s.hasNext()) {
                final String task = s.nextLine();
                Task newTask = convertTextToTask(task);
                tasks.addTaskWithoutMessage(newTask);
            }
        } catch (IOException e) {
            PrintUtils.printErrorMessage(ERROR_READING_SAVE_FILE);
        } catch (DukeException e) {
            PrintUtils.printErrorMessage(ERROR_CONVERTING_SAVE_FILE);
        }
        return tasks;
    }

    private static Task convertTextToTask(String task) throws DukeException {
        final String[] taskDetails = task.split("\\|");
        final String taskType = taskDetails[0];
        final boolean isDone = taskDetails[1].equals("1");
        switch (taskType) {
        case "T":
            String todoDescription = taskDetails[2];
            return new Todo(todoDescription, isDone);
        case "D":
            String deadlineDescription = taskDetails[2];
            String deadlineBy = taskDetails[3];
            return new Deadline(deadlineDescription, deadlineBy, isDone);
        case "E":
            String eventDescription = taskDetails[2];
            String eventAt = taskDetails[3];
            return new Event(eventDescription, eventAt, isDone);
        default:
            throw new DukeException(ExceptionMessages.EXCEPTION_INVALID_FILE_LINE);
        }
    }


}
