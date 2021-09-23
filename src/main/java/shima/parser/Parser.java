package shima.parser;

import shima.command.*;
import shima.design.Default;
import shima.storage.Storage;
import shima.task.TaskList;

import java.io.IOException;
import java.util.Scanner;

public class Parser {
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
    public static final String EMPTY_TASK_MSG = "Sorry, the task is empty! I don't know how to record it :(";
    public static final String SLASH_MISSING_MSG = "Sorry, fail to create an Event, the time specific character '/' is missing";
    public static final String DASH_MISSING_MSG = "Sorry, fail to create an Event, the period specific character '-' is missing";
    public static final String EMPTY_TASK_INDEX_MSG = "Sorry, the input task number is missing, please try again! :(";
    public static String name = "";
    public static final String EMPTY_DEADLINE_MSG = "Sorry, the deadline for the task \"" + name + "\" is missing!";
    public static final String EMPTY_PERIOD_MSG = "Sorry, the date and period for the task \"" + name + "\" is missing!";

    /**
     * Reads the input command entered by the user and handle each command
     * @param tasks The list class object that stores all the tasks
     * @param storage The storage class object that used to save data
     * @return Returns the respective command to each input accordingly
     * @throws IOException Throws this exception when there is error occurs when accessing external file
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
            return parseToDo(tasks, command, words);
        case EVENT:
            return parseEvent(tasks, command, words);
        case DEADLINE:
            return parseDeadline(tasks, command, words);
        case DONE:
            return parseDoneCommand(tasks, storage, words);
        default:
            Default.showMessage(INVALID_COMMAND_MSG);
            return new Command();
        }
    }

    /**
     * Checks the syntax for the command to create a new task
     *
     * @param words The array of words that compose the input command
     * @return Returns true if an instance of the subclass is created and successfully stored in the to-do list
     */
    private static boolean isCorrectToDo(String[] words) {
        if (words.length == 1) {
            Default.showMessage(EMPTY_TASK_MSG);
            return false;
        }
        return true;
    }

    /**
     * Checks the syntax for the command to create an 'Event' instance
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
            Default.showMessage(EMPTY_TASK_MSG);
            return false;
        }
        if (time.isEmpty()) {
            name = taskName;
            Default.showMessage(EMPTY_PERIOD_MSG);
            return false;
        }
        if (!command.contains("/")) {
            Default.showMessage(SLASH_MISSING_MSG);
            return false;
        }
        if (!command.contains("-")) {
            Default.showMessage(DASH_MISSING_MSG);
            return false;
        }
        return true;
    }

    /**
     * Checks the syntax for the command to create an 'Deadline' instance
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
            Default.showMessage(EMPTY_TASK_MSG);
            return false;
        }
        if (time.isEmpty()) {
            name = taskName;
            Default.showMessage(EMPTY_DEADLINE_MSG);
            return false;
        }
        if (!command.contains("/")) {
            Default.showMessage(SLASH_MISSING_MSG);
            return false;
        }
        return true;
    }

    /**
     * Checks if the to-do command is correct and creates a new add command to execute
     * @param tasks The list class object that stores all the tasks
     * @param command The user input command
     * @param words The array of words that compose the command
     * @return Returns the add command object if the input command is valid, returns empty command object otherwise
     */
    private static Command parseToDo(TaskList tasks, String command, String[] words) {
        if (isCorrectToDo(words)){
            return new AddCommand(tasks, command, words);
        }
        return new Command();
    }

    /**
     * Checks if the deadline command is correct and creates a new add command to execute
     * @param tasks The list class object that stores all the tasks
     * @param command The user input command
     * @param words The array of words that compose the command
     * @return Returns the add command object if the input command is valid, returns empty command object otherwise
     */
    private static Command parseDeadline(TaskList tasks, String command, String[] words) {
        if (isCorrectDeadline(command, words)) {
            return new AddCommand(tasks, command, words);
        }
        return new Command();
    }

    /**
     * Checks if the event command is correct and creates a new add command to execute
     * @param tasks The list class object that stores all the tasks
     * @param command The user input command
     * @param words The array of words that compose the command
     * @return Returns the add command object if the input command is valid, returns empty command object otherwise
     */
    private static Command parseEvent(TaskList tasks, String command, String[] words) {
        if (isCorrectEvent(command, words)){
            return new AddCommand(tasks, command, words);
        }
        return new Command();
    }

    private static Command parseDoneCommand(TaskList tasks, Storage storage, String[] words){
        if (words.length == 1) {
            Default.showMessage(EMPTY_TASK_INDEX_MSG);
            return new Command();
        }
        return new DoneCommand(tasks, storage, words);
    }
}
