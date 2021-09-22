package duke.parser;

import duke.command.Command;
import duke.exception.EmptyCommandException;
import duke.exception.IllegalCommandException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import duke.ui.Ui;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class Parser {

    private final static String DEADLINE_ICON = "[D]";
    private final static String EVENT_ICON = "[E]";
    private final static String TODO_ICON = "[T]";
    private final static String DONE_ICON = "[X]";

    private final static String DATA_SEP = "\\|";
    private final static String COMMAND_SEP = " ";

    private final static int TODO_LENGTH = 4;
    private final static int DEADLINE_LENGTH = 8;
    private final static int EVENT_LENGTH = 5;
    private final static String DEADLINE_SEP = "/by";
    private final static String EVENT_SEP = "/at";

    public Parser() {
    }

    /**
     * Creates a Task object from the saved data file to be added to a task array in Duke.
     *
     * @param taskInfo The description of the saved task
     * @return The Task object to be added to the task array
     * @throws IOException If an invalid line is found in the data file
     */
    public static Task parseTaskFromData(String taskInfo) throws IOException {
        String[] parsedData = taskInfo.split(DATA_SEP);
        Task newTask;

        switch (parsedData[0].strip()) {
        case DEADLINE_ICON:
            LocalDateTime parsedBy = parseByFromData(parsedData[3].strip());
            newTask = new Deadline(parsedData[2].strip(), parsedBy);
            break;
        case EVENT_ICON:
            newTask = new Event(parsedData[2].strip(), parsedData[3].strip());
            break;
        case TODO_ICON:
            newTask = new Todo(parsedData[2].strip());
            break;
        default:
            throw new IOException();
        }

        String parsedDoneIcon = parsedData[1].strip();
        if (parsedDoneIcon.equals(DONE_ICON)) {
            newTask.setDone(true);
        }
        return newTask;
    }

    public static LocalDateTime parseByFromData(String byFromData) {
        return LocalDateTime.parse(byFromData, Deadline.byFormat);
    }

    public static void handleCommand(String fullCommand) throws EmptyCommandException, IllegalCommandException, IOException {
        String[] parsedCommand = fullCommand.split(COMMAND_SEP);

        switch (parsedCommand[0].toUpperCase()) {
        case "LIST":
            Ui.printList();
            break;
        case "TODO":
            parseAndAddTodo(fullCommand);
            break;
        case "DEADLINE":
            parseAndAddDeadline(fullCommand);
            break;
        case "EVENT":
            parseAndAddEvent(fullCommand);
            break;
        case "DONE":
            parseAndExecuteDone(parsedCommand);
            break;
        case "DELETE":
            parseAndExecuteDelete(parsedCommand);
            break;
        default:
            throw new IllegalCommandException();
        }
    }

    public static void parseAndAddTodo(String fullCommand) throws IOException {
        String description = fullCommand.substring(TODO_LENGTH).strip();
        Command.addTodo(description);
    }

    public static void parseAndAddDeadline(String fullCommand) throws IOException, IllegalCommandException {
        String commandDescription = fullCommand.substring(DEADLINE_LENGTH).strip();
        String[] descWithBy = commandDescription.split(DEADLINE_SEP);
        if (descWithBy.length != 2) {
            throw new IllegalCommandException();
        }
        String trimmedDesc = descWithBy[0].strip();
        String trimmedBy = descWithBy[1].strip();
        LocalDateTime byAsDateTime = parseByFromCommand(trimmedBy);
        Command.addDeadline(trimmedDesc, byAsDateTime);
    }

    private static LocalDateTime parseByFromCommand(String trimmedBy) throws IllegalCommandException {
        LocalDateTime byAsDateTime;
        try {
             byAsDateTime = LocalDateTime.parse(trimmedBy, Deadline.byFormat);
        } catch (DateTimeParseException e) {
            throw new IllegalCommandException();
        }
        return byAsDateTime;
    }

    public static void parseAndAddEvent(String fullCommand) throws IOException, EmptyCommandException {
        String commandDescription = fullCommand.substring(EVENT_LENGTH).strip();
        String[] descWithAt = commandDescription.split(EVENT_SEP);
        if (descWithAt.length < 2) {
            throw new EmptyCommandException();
        }
        String[] trimmedDescWithAt = new String[descWithAt.length];
        for (int i = 0; i < descWithAt.length; i++) {
            trimmedDescWithAt[i] = descWithAt[i].strip();
        }
        Command.addEvent(trimmedDescWithAt[0], trimmedDescWithAt[1]);
    }

    public static void parseAndExecuteDone(String[] parsedCommand) throws IllegalCommandException, IOException {
        try {
            int taskIndex = Integer.parseInt(parsedCommand[1]);
            Command.doneTask(taskIndex);
        } catch (NumberFormatException e) {
            throw new IllegalCommandException();
        }
    }

    private static void parseAndExecuteDelete(String[] parsedCommand) throws IllegalCommandException, IOException {
        try {
            int taskIndex = Integer.parseInt(parsedCommand[1]);
            Command.deleteTask(taskIndex);
        } catch (NumberFormatException e) {
            throw new IllegalCommandException();
        }
    }
}
