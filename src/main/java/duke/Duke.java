package duke;

import duke.exceptions.EmptyDescriptionException;
import duke.exceptions.EmptyTimeFieldException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.PipedInputStream;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Chatbot program Duke that tracks user tasks.
 *
 * @author Leow Yuan Yang
 * @version 4.0
 * @since 2021-08-25
 */
public class Duke implements PrintOutput {
    protected static ArrayList<Task> tasks = new ArrayList<>();
    private static final String FILE_PATH = "data/userData.txt";
    private static final String FILE_DIRECTORY = "data";
    private static final String COMMAND_BYE = "bye";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_DONE = "done";
    private static final String COMMAND_DELETE = "delete";
    private static final String COMMAND_TODO = "todo";
    private static final String COMMAND_DEADLINE = "deadline";
    private static final String COMMAND_EVENT = "event";
    private static boolean isDone = false;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        PrintOutput.printWelcomeMessage();
        loadData();

        do {
            try {
                Parser inputHandler = readUserInput(in);

                // Execute commands
                switch (inputHandler.getCommand()) {
                case "bye":
                    executeBye();
                    break;
                case "list":
                    executeList();
                    break;
                case "done":
                    executeDone(inputHandler.getTaskIndex());
                    break;
                case "todo":
                    executeTodo(inputHandler.getDescription());
                    break;
                case "deadline":
                    executeDeadline(inputHandler.getDescription(), inputHandler.getTimeField());
                    break;
                case "event":
                    executeEvent(inputHandler.getDescription(), inputHandler.getTimeField());
                    break;
                case "delete":
                    executeDelete(inputHandler.getTaskIndex());
                    break;
                default:
                    PrintOutput.printErrorMessage();
                    break;
                }
            } catch (EmptyDescriptionException e) {
                PrintOutput.printEmptyDescriptionErrorMessage();
            } catch (EmptyTimeFieldException e) {
                PrintOutput.printEmptyTimeFieldErrorMessage();
            } catch (Exception e) {
                PrintOutput.printErrorMessage();
            }
        } while (!isDone);
    }

    private static void saveData() {
        try {
            writeToFile();
        } catch (IOException exception) {
            System.out.println("something went wrong while saving..." + exception.getMessage());
        }
    }

    private static void writeToFile() throws  IOException {
        FileWriter fileWriter = new FileWriter(FILE_PATH);
        for (Task t : tasks) {
            String taskData = "";
            if (t instanceof Todo) {
                taskData = t.getTaskIcon().concat(" | ")
                        .concat(t.getStatusIcon()).concat(" | ")
                        .concat(t.getDescription()).concat(System.lineSeparator());
            } else if (t instanceof Event || t instanceof Deadline){
                taskData = t.getTaskIcon().concat(" | ")
                        .concat(t.getStatusIcon()).concat(" | ")
                        .concat(t.getDescription()).concat(" | ")
                        .concat(t.getDue()).concat(System.lineSeparator());
            }
            fileWriter.write(taskData);
        }
        fileWriter.close();
    }

    private static void loadData() {
        try {
            File file = loadFile();
            Scanner scanner = new Scanner(file);
            while(scanner.hasNext()) {
                String data = scanner.nextLine();
                String[] dataSubstrings = data.split("\\|");
                String type = dataSubstrings[0].strip();
                boolean done = dataSubstrings[1].strip().equals("X");
                String description = dataSubstrings[2].strip();
                String due;

                switch (type) {
                case "T":
                    Task todoTask = new Todo(description);
                    if (done) {
                        todoTask.setDone();
                    }
                    tasks.add(todoTask);
                    break;
                case "D":
                    due = dataSubstrings[3].strip();
                    Task deadlineTask = new Deadline(description, due);
                    if (done) {
                        deadlineTask.setDone();
                    }
                    tasks.add(deadlineTask);
                    break;
                case "E":
                    due = dataSubstrings[3].strip();
                    Task eventTask = new Event(description, due);
                    if (done) {
                        eventTask.setDone();
                    }
                    tasks.add(eventTask);
                    break;
                }
            }
        } catch (IOException exception) {
            System.out.println("oops something went wrong..." + exception.getMessage());
        }
    }

    private static File loadFile() throws IOException {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            File dir = new File(FILE_DIRECTORY);
            dir.mkdir();
            File newFile = new File(FILE_PATH);
            newFile.createNewFile();
            return newFile;
        } else {
            return file;
        }
    }

    private static void executeBye() {
        isDone = true;
        saveData();
        PrintOutput.printExitMessage();
    }

    private static void executeEvent(String description, String timeField) {
        Task newTask = new Event(description, timeField);
        tasks.add(newTask);
        PrintOutput.printAddTask(newTask);
    }

    private static void executeDeadline(String description, String timeField) {
        Task newTask = new Deadline(description, timeField);
        tasks.add(newTask);
        PrintOutput.printAddTask(newTask);
    }

    private static void executeTodo(String description) throws EmptyDescriptionException {
        Task newTask = new Todo(description);
        tasks.add(newTask);
        PrintOutput.printAddTask(newTask);
    }

    private static void executeDone(int taskIndex) {
        Task doneTask = tasks.get(taskIndex - 1);
        doneTask.setDone();
        Task.decrementNumOfTasks();
        PrintOutput.printDoneTask(doneTask, taskIndex);
    }

    private static void executeDelete(int taskIndex) {
        Task deletedTask = tasks.get(taskIndex - 1);
        tasks.remove(taskIndex - 1);
        PrintOutput.printDeleteTask(deletedTask);
    }

    private static void executeList() {
        PrintOutput.printTaskList(tasks);
    }

    private static Parser readUserInput(Scanner in) throws EmptyDescriptionException, EmptyTimeFieldException {
        System.out.println("\tCall out a smurf to do a job for you!");
        return new Parser(in.nextLine());
    }
}
