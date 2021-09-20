package duke;

import duke.command.*;
import duke.task.Task;

import java.io.*;
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

    // load data from data file
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

    // save data into data file
    public static void exportData() {
        try {
            FileWriter fileWriter = new FileWriter(FILE_PATH);
            for (Task task : TaskManager.getTasklist()) {
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

    // encode line into format <TaskType> <Status> <Title> <Time>
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
            time = task.getTime();
            break;
        case EVENT:
            taskType = EVENT_CODE;
            time = task.getTime();
            break;
        default:
            throw new DukeException("Invalid task found in tasklist");
        }

        status = task.getStatusIcon();
        title = task.getName();

        return String.format("%s%s%s%s%s%s%s", taskType, DELIMITER, status, DELIMITER, title, DELIMITER, time);
    }

    // decode line of format <TaskType> <Status> <Title> <Time>
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
            int taskNo = TaskManager.getTasklistSize();
            command = new DoneCommand(taskNo - 1);
            command.run(false);
        }
    }
}
