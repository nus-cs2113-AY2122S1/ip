package duke.command;

import duke.Message;
import duke.Parser;
import duke.exception.InvalidCommandException;
import duke.task.TaskManager;
import duke.task.Type;

public class CommandManager {
    private static final String UNKNOWN_COMMAND_MESSAGE = "OOPS!!! I'm sorry, but I don't know what that means :-(";

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

    public static boolean handleCommand(String userInput) {
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
        } catch (
                InvalidCommandException ive) {
            Message.printWithSpacers(ive.getMessage());
        }
        return true;
    }

}
