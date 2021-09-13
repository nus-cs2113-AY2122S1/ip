package duke;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DoneCommand;
import duke.task.Task;
import duke.task.TaskType;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    private static final String FILE_NAME = "duke.txt";
    private static final String DELIMITER = "@@@";

    private static final int TASK_TYPE = 0;
    private static final int TASK_IS_DONE = 1;
    private static final int TASK_NAME = 2;
    private static final int TASK_DATE = 3;

    private static final String ERROR_LOAD = "     File not found. A new list will be started.";
    private static final String ERROR_SAVE = "     Save file not found. List of tasks will not be saved";

    /**
     * Loads task data from the file when Duke starts up.
     */
    public static void loadTask() {
        checkFileExist();
        try {
            FileInputStream fileInputStream = new FileInputStream(FILE_NAME);
            Scanner scanner = new Scanner(fileInputStream);
            while (scanner.hasNext()) {
                parseLine(scanner.nextLine());
            }
        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println(ERROR_LOAD);
        } catch (DukeException dukeException) {
            dukeException.getMessage();
        }
    }

    /**
     * Checks if the file exists, otherwise new file will be created.
     */
    private static void checkFileExist() {
        File file = new File(System.getProperty("user.dir"), FILE_NAME);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

    }

    /**
     * Make sense of the string line data loaded from the file
     * and adds the saved tasks into the list.
     *
     * @param line Single line read from the file as a string.
     * @throws DukeException If there is an error with the string format on the file.
     */
    private static void parseLine(String line) throws DukeException {
        String[] words = line.split(DELIMITER);
        Command addCommand = null;
        switch(words[TASK_TYPE]) {
        case "T":
            addCommand = new AddCommand(words[TASK_NAME], null, TaskType.TODO);
            break;
        case "D":
            addCommand = new AddCommand(words[TASK_NAME], words[TASK_DATE], TaskType.DEADLINE);
            break;
        case "E":
            addCommand = new AddCommand(words[TASK_NAME], words[TASK_DATE], TaskType.EVENT);
        }
        assert addCommand != null;
        addCommand.runAddTaskFromFile();
        if (words[TASK_IS_DONE].equals("1")) {
            Command doneCommand = new DoneCommand(TaskManager.getListSize());
            doneCommand.runTaskDoneFromFile();
        }
    }

    /**
     * Save the tasks in the file upon exiting Duke.
     */
    public static void saveTask() {
        try {
            FileWriter fileWriter = new FileWriter(FILE_NAME);
            for (Task task : TaskManager.getTaskList()) {
                if (task != null) {
                    fileWriter.write(task.toFileString() + System.lineSeparator());
                }
            }
            fileWriter.close();
        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println(ERROR_SAVE);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
