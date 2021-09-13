package duke;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DoneCommand;
import duke.task.Task;
import duke.task.TaskType;

import java.io.*;
import java.util.Scanner;

public class Storage {
    private static final String filePath = "data/duke.txt";
    private static final String DELIMITER = "@@@";

    private static final int TASK_TYPE = 0;
    private static final int TASK_IS_DONE = 1;
    private static final int TASK_NAME = 2;
    private static final int TASK_DATE = 3;

    private static int tasksInList = 0;

    public static void loadTask() {
        try {
            FileInputStream fileInputStream = new FileInputStream(filePath);
            Scanner scanner = new Scanner(fileInputStream);
            while (scanner.hasNext()) {
                parseLine(scanner.nextLine());
            }
        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println("File not found. A new list will be started.");
        } catch (DukeException dukeException) {
            dukeException.getMessage();
        }
    }

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
        tasksInList++;
        if (words[TASK_IS_DONE].equals("1")) {
            Command doneCommand = new DoneCommand(tasksInList);
            doneCommand.runTaskDoneFromFile();
        }
    }

    public static void saveTask() {
        try {
            FileWriter fileWriter = new FileWriter(filePath);
            for (Task task : TaskManager.getTasks()) {
                if (task != null) {
                    fileWriter.write(task.toFileString() + System.lineSeparator());
                }
            }
            fileWriter.close();
        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println("Save file not found. List of tasks will not be saved");
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
