package duke.ui;

import duke.command.Command;
import duke.task.TaskList;

public class Parser {

    /**
     * Processes the user's input.
     * Retrieves the command the user gave.
     * Calls corresponding methods from TaskList to handle the command.
     * If the command in invalid, a NullPointerException is thrown implicitly.
     * If NullPointerException is caught, does nothing and returns true.
     * Main error handling is done in helper method, retrieveUserCommand().
     *
     * @param userInput User's input.
     * @return A boolean value which indicates whether the userInput the program should continue running.
     */
    public static boolean processUserInput(String userInput) {
        try {
            Command userCommand = retrieveUserCommand(userInput);
            switch (userCommand) {
            case DONE:
                TaskList.markDone(userInput);
                return true;
            case LIST:
                TaskList.printTasks();
                return true;
            case TODO:
                TaskList.addTodo(userInput);
                return true;
            case EVENT:
                TaskList.addEvent(userInput);
                return true;
            case DEADLINE:
                TaskList.addDeadline(userInput);
                return true;
            case DELETE:
                TaskList.deleteTask(userInput);
                return true;
            case BYE:
                return false;
            default:
                return true;
            }
        } catch (NullPointerException e) {
            return true;
        }
    }

    /**
     * Retrieves the user's command from the user's input.
     * If command is not valid, throws an IllegalArgumentException.
     * If IllegalArgumentException is caught, prints error message and returns null.
     * If no IllegalArgumentException is thrown, returns a Command type enum.
     * A helper method for processUserInput().
     *
     * @param userInput User's input.
     * @return An enum of type Command.
     */
    private static Command retrieveUserCommand(String userInput) {
        String commandString;
        int indexOfSpace = userInput.indexOf(' ');
        if (indexOfSpace == -1) {
            commandString = userInput.substring(0);
        } else {
            commandString = userInput.substring(0, indexOfSpace);
        }
        try {
            Command userCommand = Command.valueOf(commandString.toUpperCase());
            return userCommand;
        } catch (IllegalArgumentException e) {
            System.out.println("OOPS!!! I'm sorry but I don't know what that means");
            return null;
        }
    }

}
