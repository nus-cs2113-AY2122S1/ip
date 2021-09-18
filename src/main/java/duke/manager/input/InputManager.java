package duke.manager.input;

import duke.manager.command.Command;
import duke.ui.UserInterface;

import java.util.Scanner;

/**
 * <h1>InputManager</h1>
 * An <code>InputManger</code> abject is in charge of managing inputs given by the user. <code>inputCommand</code>
 * is an enum for commands which is assigned by <code>setInputCommand()</code> whenever <code>readInput()</code>
 * is called.
 * <code>commandArguments</code>, which represents the arguments tied to the inputted command, is also assigned a value
 * whenever <code>readInput()</code> is called.
 * <code>inputCommand</code> and <code>commandArguments</code> can subsequently be read through their getter methods
 * when executing commands.
 */
public class InputManager {

    private Scanner scanner;
    private Command inputCommand;
    private String commandArguments;
    private static final String INPUT_BYE = "bye";
    private static final String INPUT_LIST = "list";
    private static final String INPUT_TODO = "todo";
    private static final String INPUT_EVENT = "event";
    private static final String INPUT_DEADLINE = "deadline";
    private static final String INPUT_DONE = "done";
    private static final String INPUT_DELETE = "delete";
    private static final String INPUT_FIND = "find";
    private static final String NO_ARGUMENT_INPUT = "none";

    public InputManager() {
        scanner = new Scanner(System.in);
    }

    public void setInputCommand(String input) {
        switch (input) {
        case INPUT_BYE:
            inputCommand = Command.EXIT;
            break;
        case INPUT_LIST:
            inputCommand = Command.SHOW_LIST;
            break;
        case INPUT_FIND:
            inputCommand = Command.FIND_KEYWORD;
            break;
        case INPUT_TODO:
            inputCommand = Command.ADD_TODO;
            break;
        case INPUT_EVENT:
            inputCommand = Command.ADD_EVENT;
            break;
        case INPUT_DEADLINE:
            inputCommand = Command.ADD_DEADLINE;
            break;
        case INPUT_DONE:
            inputCommand = Command.DONE_TASK;
            break;
        case INPUT_DELETE:
            inputCommand = Command.DELETE_TASK;
            break;
        default:
            inputCommand = Command.INVALID;
            break;
        }
    }

    public Command getInputCommand() {
        return inputCommand;
    }

    public String getCommandArguments() {
        return commandArguments;
    }

    /**
     * Reads in user input and parses it.
     * <code>inputCommand</code> and <code>commandArguments</code> are then set based on the parsed input.
     */
    public void readInput() {
        System.out.print(UserInterface.INPUT_PROMPT);
        String input = scanner.nextLine();
        Parser parsedInput = new Parser(input);
        if (parsedInput.isMoreThanOneWord()) {
            String firstWord = parsedInput.getFirstWord();
            setInputCommand(firstWord);
            commandArguments = parsedInput.getArguments()[1].trim();
        } else {
            setInputCommand(parsedInput.getInput()); //one word/empty string
            commandArguments = NO_ARGUMENT_INPUT;
        }
    }
}
