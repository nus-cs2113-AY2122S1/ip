package duke.parser;

import duke.command.Command;
import duke.exception.*;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class Parser {
    private static final int LENGTH_OF_DONE = 4;
    private static final int LENGTH_OF_TODO = 4;
    private static final int LENGTH_OF_FIND = 4;
    private static final int LEN_OF_YEAR = 4;
    private static final int LENGTH_OF_EVENT = 5;
    private static final int LENGTH_OF_DELETE = 6;
    private static final int LENGTH_OF_DEADLINE = 8;
    private static final int LENGTH_OF_SHOW_DATE = 9;
    private static final int TOTAL_NUM_MONTH = 12;
    private static final int TOTAL_NUM_DAY = 31;
    private static final int MIN_SPLIT_SIZE = 2;
    private static final int CMD_NOT_FOUND = -1;
    private static final int CMD_TODO = 1;
    private static final int CMD_EVENT = 2;
    private static final int CMD_DEADLINE = 3;
    private static final int CMD_LIST = 4;
    private static final int CMD_DONE = 5;
    private static final int CMD_DELETE = 6;
    private static final int CMD_SHOW_DATE = 7;
    private static final int CMD_FIND = 8;
    private static final int CMD_TERMINATE = 0;
    private static final String LIST = "list";
    private static final String SHOW_DATE = "show date";
    private static final String TODO = "todo";
    private static final String EVENT = "event";
    private static final String DEADLINE = "deadline";
    private static final String DONE = "done";
    private static final String FIND = "find";
    private static final String BYE = "bye";
    private static final String DELETE = "delete";
    private static final String BY = "/by";
    private static final String AT = "/at";

    private static boolean isInvalid(TaskList task, String line, String key) {
        if (!line.split(key)[1].trim().isEmpty()) {
            if (Integer.parseInt(line.split(key)[1].trim()) > task.getTaskCount()) {
                return true;
            }
        }
        return (line.length() <= LENGTH_OF_EVENT);
    }

    /**
     * Extracts the command parameter from the user input
     *
     * @param fullCommand Input provided by user
     * @return Command object of the given command
     */
    public static Command parse(String fullCommand) {
        Command c;
        c = new Command();
        c.setUserInput(fullCommand);
        if (fullCommand.matches(LIST)) {
            c.setCommand(CMD_LIST);
        } else if (fullCommand.length() > LENGTH_OF_DONE && fullCommand.substring(0, LENGTH_OF_DONE).contains(DONE)) {
            c.setCommand(CMD_DONE);
        } else if (fullCommand.length() >= LENGTH_OF_TODO && fullCommand.substring(0, LENGTH_OF_TODO).contains(TODO)) {
            c.setCommand(CMD_TODO);
        } else if (fullCommand.length() >= LENGTH_OF_EVENT && fullCommand.substring(0, LENGTH_OF_EVENT).contains(EVENT)) {
            c.setCommand(CMD_EVENT);
        } else if (fullCommand.length() >= LENGTH_OF_DEADLINE && fullCommand.substring(0, LENGTH_OF_DEADLINE).contains(DEADLINE)) {
            c.setCommand(CMD_DEADLINE);
        } else if (fullCommand.length() >= LENGTH_OF_DELETE && fullCommand.substring(0, LENGTH_OF_DELETE).contains(DELETE)) {
            c.setCommand(CMD_DELETE);
        } else if (fullCommand.length() >= LENGTH_OF_SHOW_DATE && fullCommand.substring(0, LENGTH_OF_SHOW_DATE).contains(SHOW_DATE)) {
            c.setCommand(CMD_SHOW_DATE);
        } else if (fullCommand.length() >= LENGTH_OF_FIND && fullCommand.substring(0, LENGTH_OF_FIND).contains(FIND)) {
            c.setCommand(CMD_FIND);
        } else if (fullCommand.matches(BYE)) {
            c.setCommand(CMD_TERMINATE);
        } else {
            c.setCommand(CMD_NOT_FOUND);
        }
        return c;
    }

    /**
     * Verify the if command is valid
     *
     * @param task List of task so far
     * @param c Command object to access and execute the command
     * @param ui Ui object to interact with the user
     * @return boolean value true if command is valid
     */
    public static boolean verifyCommand(TaskList task, Command c, Ui ui) {
        String userInput = c.getUserInput();
        switch (c.getCommand()) {
            case CMD_TODO:
                try {
                    if (userInput.length() <= LENGTH_OF_TODO + 1) {
                        throw new EmptyDescriptionException();
                    }
                } catch (EmptyDescriptionException e) {
                    ui.printEmptyDescriptionError(TODO);
                    return true;
                }
                return false;
            case CMD_EVENT:
                try {
                    String[] keys = userInput.replace(EVENT, "").split(AT);
                    if (keys.length < MIN_SPLIT_SIZE || keys[0].isBlank() || keys[1].isBlank()) {
                        throw new EmptyDescriptionException();
                    }
                } catch (EmptyDescriptionException e) {
                    ui.printEmptyDescriptionError(EVENT);
                    return true;
                }
                return false;
            case CMD_DEADLINE:
                try {
                    String[] keys = userInput.replace(DEADLINE, "").split(BY);
                    if (keys.length < MIN_SPLIT_SIZE || keys[0].isBlank() || keys[1].isBlank()) {
                        throw new EmptyDescriptionException();
                    }
                } catch (EmptyDescriptionException e) {
                    ui.printEmptyDescriptionError(DEADLINE);
                    return true;
                }
                return false;
            case CMD_SHOW_DATE:
                String key = userInput.replace(SHOW_DATE, "");
                try {
                    if (key.isBlank()) {
                        throw new ShowDateIsEmptyException();
                    }
                } catch (ShowDateIsEmptyException e) {
                    ui.printEmptyDateError();
                    return true;
                }
                try {
                    if (Integer.parseInt(key.split("/")[1].trim()) > TOTAL_NUM_MONTH) {
                        throw new WrongDateFormatException();
                    }
                    if (Integer.parseInt(key.split("/")[2].trim()) > TOTAL_NUM_DAY) {
                        throw new WrongDateFormatException();
                    }
                } catch (WrongDateFormatException e) {
                    ui.printWrongDateFormatError();
                    return true;
                }
                try {
                    if (key.split("/")[0].trim().length() < LEN_OF_YEAR) {
                        throw new WrongDateFormatException();
                    }
                } catch (WrongDateFormatException e) {
                    ui.printWrongDateFormatError();
                    return true;
                }
                return false;
            case CMD_FIND:
                try {
                    key = userInput.replace(FIND, "");
                    if (key.isBlank()) {
                        throw new FindIsEmptyException();
                    }
                } catch (FindIsEmptyException e) {
                    ui.printFindFieldEmpty();
                    return true;
                }
                return false;
            case CMD_DONE:
                try {
                    if (isInvalid(task, userInput, DONE)) {
                        throw new CommandInvalidException();
                    }
                } catch (CommandInvalidException e) {
                    ui.printCommandIsInvalid();
                    return true;
                }
                return false;
            case CMD_DELETE:
                try {
                    if (isInvalid(task, userInput, DELETE)) {
                        throw new CommandInvalidException();
                    }
                } catch (CommandInvalidException e) {
                    ui.printCommandIsInvalid();
                    return true;
                }
                return false;
            case CMD_LIST:
            case CMD_TERMINATE:
                return false;
            default:
                try {
                    throw new CommandDoesNotExistException();
                } catch (CommandDoesNotExistException e) {
                    ui.printCommandDoesNotExist();
                    return true;
                }
        }
    }
}