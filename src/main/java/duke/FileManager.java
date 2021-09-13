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


    public static void writeTaskListToFile(TaskList taskList) {
        if (!Files.exists(FILE_PATH)) {
            createFile();
        }
        ;
        try {
            FileWriter fw = new FileWriter(FILE_PATH.toString());
            fw.write(taskList.toFile());
            fw.close();
        } catch (IOException e) {
            System.out.println("Error writing to save file");
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
            PrintUtils.printErrorMessage("Error reading save file, some tasks may have been lost");
        } catch (DukeException e) {
            PrintUtils.printErrorMessage("Error converting file to task list, some tasks may have been lost");
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
            return new Todo(todoDescription);
        case "D":
            String deadlineDescription = taskDetails[2];
            String deadlineBy = taskDetails[3];
            return new Deadline(deadlineDescription, deadlineBy);
        case "E":
            String eventDescription = taskDetails[2];
            String eventAt = taskDetails[3];
            return new Event(eventDescription, eventAt);
        default:
            throw new DukeException(ExceptionMessages.EXCEPTION_INVALID_FILE_LINE);
        }
    }

    private static void createFile() {
        try {
            File newFile = new File(FILE_PATH.toString());
            newFile.createNewFile();
        } catch (IOException e) {
            PrintUtils.printErrorMessage("Error creating save file");
        }
    }

}
