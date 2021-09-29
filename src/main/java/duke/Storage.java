package duke;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Scanner;

import duke.exception.DukeException;
import duke.exception.ExceptionMessages;
import duke.task.TaskList;
import duke.task.Task;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

/**
 * Deals with loading tasks from save file and saving tasks to the save file.
 */
public class Storage {
    private static final String ROOT = System.getProperty("user.dir");
    private static final Path FILE_PATH = Paths.get(ROOT, "data", "duke.txt");
    private static final Path DIRECTORY_PATH = Paths.get(ROOT, "data");

    /**
     * Saves a <code>TaskList</code> object to a file in a relative path specified by FILE_PATH
     * by converting it to a text file.
     * If the file or the target directory does not exist, it will be created.
     *
     * @param taskList The task list to be saved.
     */
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
            Ui.showWritingToSaveFileError();
        } catch (DukeException e) {
            switch (e.getMessage()) {
            case ExceptionMessages.EXCEPTION_CREATE_FILE_FAIL:
                Ui.showCreateSaveFileError();
                break;
            case ExceptionMessages.EXCEPTION_CREATE_DIRECTORY_FAIL:
                Ui.showCreateDirectoryError();
                break;
            default:
                Ui.showUnknownError();
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
            Ui.showCreateSaveFileError();
        }
    }

    /**
     * Loads task list stored in the text save file and converts it to a <code>TaskList</code> object.
     *
     * @return TaskList object obtained by converting text save file to a TaskList object.
     */
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
            Ui.showReadSaveFileError();
        } catch (DukeException e) {
            Ui.showConvertSaveFileError();
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
            LocalDateTime deadlineBy = LocalDateTime.parse(taskDetails[3]);
            return new Deadline(deadlineDescription, deadlineBy, isDone);
        case "E":
            String eventDescription = taskDetails[2];
            LocalDateTime eventAt = LocalDateTime.parse(taskDetails[3]);
            return new Event(eventDescription, eventAt, isDone);
        default:
            throw new DukeException(ExceptionMessages.EXCEPTION_INVALID_FILE_LINE);
        }
    }


}
