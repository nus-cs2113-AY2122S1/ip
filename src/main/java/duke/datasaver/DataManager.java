package duke.datasaver;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import duke.exception.FileTaskInvalidFormatException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import java.util.ArrayList;
import java.util.Scanner;

public class DataManager {
    private static final String DIRECTORY_NAME = "dukeData";
    private static final String FILE_NAME = "tasks.txt";
    private static final String FILE_PATH = DIRECTORY_NAME + "\\" + FILE_NAME;
    protected static final String ATTRIBUTE_SEPARATOR = " | ";
    protected static final String DONE = "X";
    protected static final String NOT_DONE = "O";

    public static void createFileInDirectory() throws IOException {
        Files.createDirectories(Paths.get(DIRECTORY_NAME));
        Files.createFile(Paths.get(FILE_PATH));
    }

    public static void loadData (ArrayList<Task> taskList) {
        File taskFile = new File(FILE_PATH);
        Scanner fileScanner = null;
        try {
            fileScanner = new Scanner(taskFile);
        } catch (FileNotFoundException fileNotFoundException) {
            try {
                createFileInDirectory();
            } catch (IOException ioException) {
                System.out.println(" Something went wrong: " + ioException.getMessage());
            }
        }

        if (fileScanner != null) {
            while (fileScanner.hasNext()) {
                String task = fileScanner.nextLine();
                try {
                    addTask(taskList, task);
                } catch (FileTaskInvalidFormatException fileTaskInvalidFormatException) {
                    fileTaskInvalidFormatException.printFileTaskInvalidFormatMessage();
                }
            }
        }
    }

    public static void saveData(ArrayList<Task> taskList) {
        try {
            FileWriter fileWriter = new FileWriter(FILE_PATH, false);
            StringBuilder formattedTask = new StringBuilder();
            formatTask(taskList, fileWriter, formattedTask);
            fileWriter.close();
        } catch (IOException ioException) {
            System.out.println(" Something went wrong: " + ioException.getMessage());
        }
    }

    public static void addTask(ArrayList<Task> taskList, String task) throws FileTaskInvalidFormatException {
        String[] taskAttributes = task.split("\\|");
        String taskType = taskAttributes[0].trim();
        String doneStatus = taskAttributes[1].trim();

        boolean hasInvalidTaskAttributes = (taskAttributes.length < 3 || taskAttributes.length > 4);
        boolean hasInvalidDoneStatus = (!doneStatus.equals(DONE) && !doneStatus.equals(NOT_DONE));
        if (hasInvalidTaskAttributes || hasInvalidDoneStatus) {
            throw new FileTaskInvalidFormatException();
        }

        boolean isDone = (doneStatus.equals(DONE));
        switch (taskType) {
        case "T":
            String todoDescription = taskAttributes[2].trim();
            Todo newTodo = new Todo(todoDescription);
            newTodo.setDone(isDone);
            taskList.add(newTodo);
            break;
        case "D":
            String deadlineDescription = taskAttributes[2].trim();
            String deadlineDeadline = taskAttributes[3].trim();
            Deadline newDeadline = new Deadline(deadlineDescription, deadlineDeadline);
            newDeadline.setDone(isDone);
            taskList.add(newDeadline);
            break;
        case "E":
            String eventDescription = taskAttributes[2].trim();
            String eventTime = taskAttributes[3].trim();
            Event newEvent = new Event(eventDescription, eventTime);
            newEvent.setDone(isDone);
            taskList.add(newEvent);
            break;
        default:
            throw new FileTaskInvalidFormatException();
        }
    }


    public static void formatTask(ArrayList<Task> taskList, FileWriter fileWriter, StringBuilder formattedTask) throws IOException {
        for (Task task : taskList) {
            appendTaskType(formattedTask, task.getTaskType());
            appendDoneStatus(formattedTask, task);
            appendTaskDescription(formattedTask, task);
            fileWriter.write(formattedTask + System.lineSeparator());
            formattedTask = new StringBuilder();
        }
    }

    public static void appendTaskType(StringBuilder formattedTask, String taskType) {
        formattedTask.append(taskType).append(ATTRIBUTE_SEPARATOR);
    }

    public static void appendDoneStatus(StringBuilder formattedTask, Task task) {
        if (task.isDone()) {
            formattedTask.append(DONE).append(ATTRIBUTE_SEPARATOR);
        } else {
            formattedTask.append(NOT_DONE).append(ATTRIBUTE_SEPARATOR);
        }
    }

    public static void appendTaskDescription(StringBuilder formattedTask, Task task) {
        if (task instanceof Todo) {
            formattedTask.append(task.getDescription());
        } else if (task instanceof Deadline) {
            formattedTask.append(task.getDescription()).append(ATTRIBUTE_SEPARATOR);
            formattedTask.append(((Deadline) task).getBy());
        } else if (task instanceof Event) {
            formattedTask.append(task.getDescription()).append(ATTRIBUTE_SEPARATOR);
            formattedTask.append(((Event) task).getWhen());
        }
    }
}
