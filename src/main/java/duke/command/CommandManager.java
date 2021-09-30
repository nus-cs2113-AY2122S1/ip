package duke.command;

import duke.Message;
import duke.Parser;
import duke.exception.InvalidCommandException;
import duke.task.TaskManager;
import duke.task.Type;

/**
 * Class that handles command inputted by user
 */
public class CommandManager {
    private static final String UNKNOWN_COMMAND_MESSAGE = "OOPS!!! I'm sorry, but I don't know what that means :-(";

    /**
     * Takes in a String representing the command and the arguments
     * and returns the Command object that is to be executed.
     *
     * @param commandString  the string that corresponds to the command that is to be executed
     * @param argumentString all the arguments entered by user after the command in one string
     * @throws InvalidCommandException When the command does not match any known command
     */
    private static Command getCommand(String commandString, String argumentString) throws InvalidCommandException {
        switch (commandString.toLowerCase()) {
        case Bye.NAME:
            return new Bye(argumentString);
        case List.NAME:
            return new List(argumentString);
        case Done.NAME:
            return new Done(argumentString);
        case Delete.NAME:
            return new Delete(argumentString);
        case Find.NAME:
            return new Find(argumentString);
        default:
            throw new InvalidCommandException(UNKNOWN_COMMAND_MESSAGE);
        }
    }

    /**
     * Takes in a String that the user entered into console and
     * checks whether to pass to {@link duke.task.TaskManager} to create
     * new task or handle the command.
     * @param userInput String that was entered by user into console
     * @return Whether the program should continue waiting for userInput.
     */
    public static boolean handleCommand(String userInput) {
        //If the command involves a task, delegate the work to TaskManager instead.
        if (userInput.matches(Type.getTaskTypesRegex())) {
            TaskManager.newTask(userInput);
            return true;
        }
        String commandString = Parser.getFirstArgument(userInput);
        String argumentString = Parser.removeFirstArgument(userInput);
        try {
            Command command = getCommand(commandString, argumentString);
            if (!command.isValid()) {
                throw new InvalidCommandException(command.getUsage());
            }
            return command.execute();
        } catch (InvalidCommandException ive) {
            Message.printWithSpacers(ive.getMessage());
        }
        return true;
    }

}
