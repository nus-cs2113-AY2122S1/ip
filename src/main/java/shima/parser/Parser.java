package shima.parser;

import shima.command.Command;
import shima.command.AddCommand;
import shima.command.DeleteCommand;
import shima.command.DoneCommand;
import shima.command.ListCommand;
import shima.command.ViewPersonalityCommand;
import shima.command.ExitCommand;
import shima.command.HelpCommand;
import shima.design.UserInterface;
import shima.storage.Storage;
import shima.task.TaskList;

import java.io.IOException;
import java.util.Scanner;

public class Parser {
    public static final String COMMAND_PROFILE = "profile";
    public static final String COMMAND_BYE = "bye";
    public static final String COMMAND_EXIT = "exit";
    public static final String COMMAND_HELP = "help";
    public static final String COMMAND_LIST = "list";
    public static final String COMMAND_DELETE = "delete";
    public static final String COMMAND_TODO = "todo";
    public static final String COMMAND_EVENT = "event";
    public static final String COMMAND_DEADLINE = "deadline";
    public static final String COMMAND_DONE = "done";

    public static final String INVALID_COMMAND_MSG = "Sorry, the command is invalid, I cant understand... ";
    public static final String HELP_SUPPORT_MSG = "To seek for help, you can type the command \"help\" to view the help menu";
    public static final String EMPTY_TASK_MSG = "Sorry, the task is empty! I don't know how to record it :(";
    public static final String SLASH_MISSING_MSG = "Sorry, fail to create an Event, the time specific character '/' is missing";
    public static final String DASH_MISSING_MSG = "Sorry, fail to create an Event, the period specific character '-' is missing";
    public static final String EMPTY_TASK_INDEX_MSG = "Sorry, the input task number is missing, please try again! :(";
    private static String name = "";
    public static final String EMPTY_DEADLINE_MSG = "Sorry, the deadline for the task \"" + name + "\" is missing!";
    public static final String EMPTY_PERIOD_MSG = "Sorry, the date and period for the task \"" + name + "\" is missing!";

    /**
     * Reads the input command entered by the user and handle each command
     *
     * @param tasks   The list class object that stores all the tasks
     * @param storage The storage class object that used to save data
     * @return Returns the respective command to each input accordingly
     * @param ui The user interface class object used to display message box
     * @throws IOException Throws this exception when there is error occurs when accessing external file
     */
    public static Command readCommand(TaskList tasks, Storage storage, UserInterface ui) throws IOException {
        Scanner in = new Scanner(System.in);
        String command = in.nextLine().trim();
        String[] words = command.split(" ");
        switch (words[0].toLowerCase()) {
        case COMMAND_PROFILE:
            return new ViewPersonalityCommand();
        case COMMAND_BYE:
        case COMMAND_EXIT:
            return new ExitCommand(tasks, storage, ui);
        case COMMAND_HELP:
            return new HelpCommand();
        case COMMAND_LIST:
            return new ListCommand(tasks);
        case COMMAND_DELETE:
            return new DeleteCommand(tasks, words);
        case COMMAND_TODO:
            return parseToDo(tasks, command, words, ui);
        case COMMAND_EVENT:
            return parseEvent(tasks, command, words, ui);
        case COMMAND_DEADLINE:
            return parseDeadline(tasks, command, words, ui);
        case COMMAND_DONE:
            return parseDoneCommand(tasks, storage, words, ui);
        default:
            ui.showMessage(INVALID_COMMAND_MSG, HELP_SUPPORT_MSG);
            return new Command();
        }
    }

    /**
     * Checks the syntax for the command to create a new task
     *
     * @param words The array of words that compose the input command
     * @param ui The user interface class object used to display message box
     * @return Returns true if an instance of the subclass is created and successfully stored in the to-do list
     */
    private static boolean isCorrectToDo(String[] words, UserInterface ui) {
        if (words.length == 1) {
            ui.showMessage(EMPTY_TASK_MSG);
            return false;
        }
        return true;
    }

    /**
     * Checks the syntax for the command to create an 'Event' instance
     *
     * @param command The input command typed by the user
     * @param words   The array of words that compose the input command
     * @param ui The user interface class object used to display message box
     * @return Returns true if an instance of the subclass Event is created and successfully stored in the to-do list
     */
    private static boolean isCorrectEvent(String command, String[] words, UserInterface ui) {
        command = command.replaceFirst(words[0], "").trim();
        String time = command.substring(command.indexOf('/') + 1).trim();
        String taskName = command.split("/", 2)[0].trim();
        if (time.toLowerCase().startsWith("at")) {
            time = time.replaceFirst("(?i)at", "").trim();
        }
        if (words.length == 1 || taskName.isEmpty()) {
            ui.showMessage(EMPTY_TASK_MSG);
            return false;
        }
        if (time.isEmpty()) {
            name = taskName;
            ui.showMessage(EMPTY_PERIOD_MSG);
            return false;
        }
        if (!command.contains("/")) {
            ui.showMessage(SLASH_MISSING_MSG);
            return false;
        }
        if (!command.contains("-")) {
            ui.showMessage(DASH_MISSING_MSG);
            return false;
        }
        return true;
    }

    /**
     * Checks the syntax for the command to create an 'Deadline' instance
     *
     * @param command The input command typed by the user
     * @param words   The array of words that compose the input command
     * @param ui The user interface class object used to display message box
     * @return Returns true if the subclass Deadline is created and successfully stored in the to-do list
     */
    private static boolean isCorrectDeadline(String command, String[] words, UserInterface ui) {
        command = command.replaceFirst(words[0], "").trim();
        String time = command.substring(command.indexOf('/') + 1).trim();
        String taskName = command.split("/", 2)[0].trim();
        if (time.toLowerCase().startsWith("by")) {
            time = time.replaceFirst("(?i)by", "").trim();
        }
        if (words.length == 1 || taskName.isEmpty()) {
            ui.showMessage(EMPTY_TASK_MSG);
            return false;
        }
        if (time.isEmpty()) {
            name = taskName;
            ui.showMessage(EMPTY_DEADLINE_MSG);
            return false;
        }
        if (!command.contains("/")) {
            ui.showMessage(SLASH_MISSING_MSG);
            return false;
        }
        return true;
    }

    /**
     * Checks if the to-do command is correct and creates a new add command to execute
     *
     * @param tasks   The list class object that stores all the tasks
     * @param command The user input command
     * @param words   The array of words that compose the command
     * @param ui The user interface class object used to display message box
     * @return Returns the add command object if the input command is valid, returns empty command object otherwise
     */
    private static Command parseToDo(TaskList tasks, String command, String[] words, UserInterface ui) {
        if (isCorrectToDo(words, ui)) {
            return new AddCommand(tasks, command, words);
        }
        return new Command();
    }

    /**
     * Checks if the deadline command is correct and creates a new add command to execute
     *
     * @param tasks   The list class object that stores all the tasks
     * @param command The user input command
     * @param words   The array of words that compose the command
     * @param ui The user interface class object used to display message box     *
     * @return Returns the add command object if the input command is valid, returns empty command object otherwise
     */
    private static Command parseDeadline(TaskList tasks, String command, String[] words, UserInterface ui) {
        if (isCorrectDeadline(command, words, ui)) {
            return new AddCommand(tasks, command, words);
        }
        return new Command();
    }

    /**
     * Checks if the event command is correct and creates a new add command to execute
     *
     * @param tasks   The list class object that stores all the tasks
     * @param command The user input command
     * @param words   The array of words that compose the command
     * @param ui The user interface class object used to display message box
     * @return Returns the add command object if the input command is valid, returns empty command object otherwise
     */
    private static Command parseEvent(TaskList tasks, String command, String[] words, UserInterface ui) {
        if (isCorrectEvent(command, words, ui)) {
            return new AddCommand(tasks, command, words);
        }
        return new Command();
    }

    /**
     *
     * @param tasks The list class object that stores all the tasks
     * @param storage The storage class object used to save data
     * @param words The array of words that compose the command
     * @param ui The user interface class object used to display message box
     * @return Returns the done command object if the input command is valid, returns an empty command otherwise
     */
    private static Command parseDoneCommand(TaskList tasks, Storage storage, String[] words, UserInterface ui) {
        if (words.length == 1) {
            ui.showMessage(EMPTY_TASK_INDEX_MSG);
            return new Command();
        }
        return new DoneCommand(tasks, storage, words);
    }
}
