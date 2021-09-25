package Duke.SaveFile;

import Duke.BackEnd.DukeParser;
import Duke.Exception.DukeException;
import Duke.TaskTypes.Task;
import Duke.TaskTypes.Todo;
import Duke.TaskTypes.Event;
import Duke.TaskTypes.Deadline;

import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.ArrayList;
import java.nio.file.Files;
import java.nio.file.Paths;

import static Duke.BackEnd.DukeParser.parseDateTime;
import static Duke.UI.DukeConstants.FORMAT_DATE_TIME_INPUT;

public class DataSaver {

    private static final String DONE_STATUS = "1";
    private static final String NOT_DONE_STATUS = "0";
    private static final String DIRECTORY_PATH = "savedData";
    private static final String FILE_PATH = "savedData\\duke.txt";
    private static final String DIVIDER = " | ";

    public static void manageLoad(ArrayList<Task> taskList) {
        File file = new File(FILE_PATH);
        Scanner scan = null;
        try {
            scan = new Scanner(file);
        } catch (FileNotFoundException fileNotFoundException) {
            createNewFile();
        }
        loadFileContents(taskList, scan);
    }

    public static void createNewFile() {
        try {
            Files.createDirectories(Paths.get(DIRECTORY_PATH));
            Files.createFile(Paths.get(FILE_PATH));
        } catch (IOException ioException) {
            DukeException.createIOException(ioException);
        }
    }

    public static void loadFileContents(ArrayList<Task> taskList, Scanner scan) {
        while (scan != null && scan.hasNext()) {
            String newTask = scan.nextLine();
            try {
                addToTaskList(taskList, newTask);
            } catch (DukeException invalidSaveFileException) {
                DukeException.invalidSaveFileException();
            }
        }
    }

    public static void addToTaskList(ArrayList<Task> taskList, String newTask) throws DukeException {
        String[] taskDetails = newTask.split("\\|");

        if (!checkValidDetails(taskDetails)) {
            throw new DukeException();
        }

        String type = taskDetails[0].trim();

        switch(type) {
        case "T":
            String todoDescription = taskDetails[2].trim();
            Todo addedTodo = new Todo(todoDescription);
            addDoneStatus(addedTodo, taskDetails);
            taskList.add(addedTodo);
            break;
        case "D":
            String deadlineDescription = taskDetails[2].trim();
            String by = taskDetails[3].trim();
            LocalDateTime deadlineBy = DukeParser.parseDateTime(by, FORMAT_DATE_TIME_INPUT);
            Deadline addedDeadline = new Deadline(deadlineDescription, deadlineBy);
            addDoneStatus(addedDeadline, taskDetails);
            taskList.add(addedDeadline);
            break;
        case "E":
            String eventDescription = taskDetails[2].trim();
            String at = taskDetails[3].trim();
            LocalDateTime eventAt = DukeParser.parseDateTime(at, FORMAT_DATE_TIME_INPUT);
            Event addedEvent = new Event(eventDescription, eventAt);
            addDoneStatus(addedEvent, taskDetails);
            taskList.add(addedEvent);
            break;
        default:
            throw new DukeException();
        }
    }

    public static boolean checkValidDetails(String[] taskDetails) {
        String status = taskDetails[1].trim();
        if (!status.equals(DONE_STATUS) && !status.equals(NOT_DONE_STATUS)) {
            return false;
        }

        return taskDetails.length == 3 || taskDetails.length == 4;
    }

    public static void addDoneStatus(Task addedTask, String[] taskDetails) {
        String status = taskDetails[1].trim();
        if (status.equals(DONE_STATUS)) {
            addedTask.markAsDone();
        }
    }

    public static void manageSave(ArrayList<Task> taskList) {
        try {
            saveFileContents(taskList);
        } catch (IOException ioException) {
            DukeException.SaveIOException(ioException);
        }
    }

    public static void saveFileContents(ArrayList<Task> taskList) throws IOException {
        FileWriter writeFile = new FileWriter(FILE_PATH);
        configureTask(taskList, writeFile);
        writeFile.close();
    }

    public static void configureTask(ArrayList<Task> taskList, FileWriter writeFile) throws IOException {
        for (Task task : taskList) {
            StringBuilder parsedTask = new StringBuilder();
            parseType(task, parsedTask);
            parseStatus(task, parsedTask);
            parseDescription(task, parsedTask);
            writeFile.write(parsedTask + System.lineSeparator());
        }
    }

    public static void parseType(Task task, StringBuilder parsedTask) {
        String type = task.getType();
        parsedTask.append(type).append(DIVIDER);
    }

    public static void parseStatus(Task task, StringBuilder parsedTask) {
        if (task.getIsDone()) {
            parsedTask.append("1").append(DIVIDER);
        } else {
            parsedTask.append("0").append(DIVIDER);
        }
    }

    public static void parseDescription(Task task, StringBuilder parsedTask) {
        parsedTask.append(task.getDescription()).append(DIVIDER);
        if (task instanceof Deadline) {
            parsedTask.append(((Deadline) task).getBy());
        } else if (task instanceof Event) {
            parsedTask.append(((Event) task).getAt());
        }
    }
}
