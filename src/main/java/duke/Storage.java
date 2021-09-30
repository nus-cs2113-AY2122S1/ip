package duke;

import duke.command.Command;
import duke.command.DoneCommand;
import duke.command.TodoCommand;
import duke.command.DeadlineCommand;
import duke.command.EventCommand;
import duke.task.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    private static final String DATA_DIR_NAME = "data";
    private static final String DATA_FILE_NAME = "duke.txt";
    private static final String FILE_PATH = DATA_DIR_NAME + File.separator + DATA_FILE_NAME;

    private static final String TODO_CODE = "T";
    private static final String DEADLINE_CODE = "D";
    private static final String EVENT_CODE = "E";
    private static final String DONE = "X";
    private static final String DELIMITER = "#&#";
    private static final String NULL = "NULL";

    /**
     * Loads task data from FILE_PATH on startup
     */
    public static void importData() {
        try {
            File directory = new File(DATA_DIR_NAME);
            if (!directory.exists()) {
                directory.mkdir();
            }
            File file = new File(FILE_PATH);
            if (!file.exists()) {
                file.createNewFile();
            }
            Scanner input = new Scanner(file);
            while (input.hasNext()) {
                decodeLine(input.nextLine());
            }
        } catch (FileNotFoundException | DukeException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Saves task data to DATA_FILE_NAME on exit
     */
    public static void exportData() {
        try {
            FileWriter fileWriter = new FileWriter(FILE_PATH);
            for (Task task : TaskList.getTasklist()) {
                if (task == null) {
                    break;
                }
                String encodedTask = encodeLine(task);
                fileWriter.write(encodedTask + System.lineSeparator());
            }
            fileWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("Data file missing!");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Converts Task object to String to be saved in data file
     *
     * @param task Task to be encoded to String used in data file
     * @return String of format <TaskType> <Status> <Title> <Time>
     * @throws DukeException If an invalid task type is found
     */
    private static String encodeLine(Task task) throws DukeException {
        String taskType = NULL;
        String status = NULL;
        String title = NULL;
        String time = NULL;

        switch (task.getTaskType()) {
        case TODO:
            taskType = TODO_CODE;
            break;
        case DEADLINE:
            taskType = DEADLINE_CODE;
            time = task.getStandardTime();
            break;
        case EVENT:
            taskType = EVENT_CODE;
            time = task.getStandardTime();
            break;
        default:
            throw new DukeException("Invalid task found in tasklist");
        }

        status = task.getStatusIcon();
        title = task.getName();

        return taskType + DELIMITER + status + DELIMITER + title + DELIMITER + time;
    }

    /**
     * Converts String in data file to Task and loads it to TaskManager
     *
     * @param line String of format <TaskType> <Status> <Title> <Time> to be decoded to Task used in data file
     * @throws DukeException If the String is invalid and does not have any associated Task object
     */
    private static void decodeLine(String line) throws DukeException {
        String[] data = line.split(DELIMITER);
        Command command;

        if (data.length != 4) {
            throw new DukeException("Unable to load data. Some data is corrupted");
        }

        final String taskType = data[0];
        final String status = data[1];
        final String title = data[2];
        final String time = data[3];

        switch (taskType) {
        case TODO_CODE:
            command = new TodoCommand(title);
            break;
        case DEADLINE_CODE:
            command = new DeadlineCommand(title, time);
            break;
        case EVENT_CODE:
            command = new EventCommand(title, time);
            break;
        default:
            throw new DukeException("Unable to load data. Some data is corrupted");
        }

        command.run(false);

        // mark most recent task as done
        if (status.equals(DONE)) {
            int taskNo = TaskList.getTasklistSize();
            command = new DoneCommand(taskNo - 1);
            command.run(false);
        }
    }
}
