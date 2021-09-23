package shima.parser;

import shima.command.*;
import shima.design.Default;
import shima.storage.Storage;
import shima.task.TaskList;

import java.io.IOException;
import java.util.Scanner;

public class Parser {
    public static final String EMPTY = "";
    public static final String PROFILE = "profile";
    public static final String BYE = "bye";
    public static final String EXIT = "exit";
    public static final String HELP = "help";
    public static final String LIST = "list";
    public static final String DELETE = "delete";
    public static final String TODO = "todo";
    public static final String EVENT = "event";
    public static final String DEADLINE = "deadline";
    public static final String DONE = "done";
    public static final String INVALID_COMMAND_MSG = "Sorry, the command is invalid, I cant understand... " +
            "To seek for help, you can type the command \"help\" or \"view -h\"";

    /**
     * Reads the input command entered by the user and handles each command
     *
     * @param tasks The array to store all the tasks required
     */
    public static Command readCommand(TaskList tasks, Storage storage) throws IOException {
        Scanner in = new Scanner(System.in);
        String command = in.nextLine().trim();
        String[] words = command.split(" ");
        switch (words[0].toLowerCase()) {
        case PROFILE:
            return new ViewPersonalityCommand();
        case BYE:
        case EXIT:
            return new ExitCommand(tasks, storage);
        case HELP:
            return new HelpCommand();
        case LIST:
            return new ListCommand(tasks);
        case DELETE:
            return new DeleteCommand(tasks, words);
        case TODO:
            return parseToDo(tasks, storage, command, words);
        case EVENT:
            return parseEvent(tasks, storage, command, words);
        case DEADLINE:
            return parseDeadline(tasks, storage, command, words);
        case DONE:
            DoneTask.handleTaskDone(tasks, words, storage);
            break;
        default:
            Default.showMessage(INVALID_COMMAND_MSG);
        }
        return new ExitCommand(tasks, storage);
    }

    /**
     * Checks the syntax for the command to create a new task, and add to the to-do list if the syntax is correct
     *
     * @param words The array of words that compose the input command
     * @return Returns true if an instance of the subclass is created and successfully stored in the to-do list
     */
    private static boolean isCorrectToDo(String[] words) {
        if (words.length == 1) {
            Default.showMessage(AddCommand.EMPTY_TASK_MSG);
            return false;
        }
        return true;
    }

    /**
     * Checks the syntax for the command to create an 'Event' instance, and add to the to-do list if the syntax is correct
     *
     * @param command The input command typed by the user
     * @param words   The array of words that compose the input command
     * @return Returns true if an instance of the subclass Event is created and successfully stored in the to-do list
     */
    private static boolean isCorrectEvent(String command, String[] words) {
        command = command.replaceFirst(words[0], "").trim();
        String time = command.substring(command.indexOf('/') + 1).trim();
        String taskName = command.split("/", 2)[0].trim();
        if (time.toLowerCase().startsWith("at")) {
            time = time.replaceFirst("(?i)at", "").trim();
        }
        if (words.length == 1 || taskName.isEmpty()) {
            Default.showMessage(AddCommand.EMPTY_TASK_MSG);
            return false;
        }
        if (time.isEmpty()) {
            AddCommand.name = taskName;
            Default.showMessage(AddCommand.EMPTY_PERIOD_MSG);
            return false;
        }
        if (!command.contains("/")) {
            Default.showMessage(AddCommand.SLASH_MISSING_MSG);
            return false;
        }
        if (!command.contains("-")) {
            Default.showMessage(AddCommand.DASH_MISSING_MSG);
            return false;
        }
        return true;
    }

    /**
     * Checks the syntax for the command to create an 'Deadline' instance, and add to the to-do list if the syntax is correct
     *
     * @param command The input command typed by the user
     * @param words   The array of words that compose the input command
     * @return Returns true if the subclass Deadline is created and successfully stored in the to-do list
     */
    private static boolean isCorrectDeadline(String command, String[] words) {
        command = command.replaceFirst(words[0], "").trim();
        String time = command.substring(command.indexOf('/') + 1).trim();
        String taskName = command.split("/", 2)[0].trim();
        if (time.toLowerCase().startsWith("by")) {
            time = time.replaceFirst("(?i)by", "").trim();
        }
        if (words.length == 1 || taskName.isEmpty()) {
            Default.showMessage(AddCommand.EMPTY_TASK_MSG);
            return false;
        }
        if (time.isEmpty()) {
            AddCommand.name = taskName;
            Default.showMessage(AddCommand.EMPTY_DEADLINE_MSG);
            return false;
        }
        if (!command.contains("/")) {
            Default.showMessage(AddCommand.SLASH_MISSING_MSG);
            return false;
        }
        return true;
    }

    public static Command parseToDo(TaskList tasks, Storage storage, String command, String[] words) {
        if (isCorrectToDo(words)){
            return new AddCommand(tasks, storage, command, words);
        }
        return new EmptyCommand();
    }

    public static Command parseDeadline(TaskList tasks, Storage storage, String command, String[] words) {
        if (isCorrectDeadline(command, words)) {
            return new AddCommand(tasks, storage, command, words);
        }
        return new EmptyCommand();
    }

    public static Command parseEvent(TaskList tasks, Storage storage, String command, String[] words) {
        if (isCorrectEvent(command, words)){
            return new AddCommand(tasks, storage, command, words);
        }
        return new EmptyCommand();
    }
}
