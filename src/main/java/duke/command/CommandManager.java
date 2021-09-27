package duke.command;

import duke.Message;
import duke.Parser;
import duke.exception.InvalidCommandException;
import duke.task.TaskManager;
import duke.task.Type;

public class CommandManager {
    private static final String NO_DESCRIPTION_MESSAGE = "OOPS!!! I'm sorry, but I don't know what that means :-(";

    private static boolean runCommand(Command command, String argument){
        switch (command) {
        case BYE:
            return false;
        case LIST:
            TaskManager.printTasks();
            break;
        case DONE:
            TaskManager.taskDone(Integer.parseInt(argument) - 1);
            break;
        case DELETE:
            TaskManager.deleteTask(Integer.parseInt(argument) - 1);
            break;
        case FIND:
            TaskManager.findTasks(argument);
            break;
        }
        return true;
    }

    public static boolean handleCommand(String userInput) {
        if (userInput.matches(Type.getTypesRegex())) {
            TaskManager.newTask(userInput);
            return true;
        }
        String[] userInputSplit = Parser.splitWhitespace(userInput);
        try {
            if (!Command.contains(userInputSplit[0])) {
                throw new InvalidCommandException(NO_DESCRIPTION_MESSAGE);
            }
            Command command = Command.getCommand(userInputSplit[0]);
            if (!command.isValid(userInput)) {
                throw new InvalidCommandException(command.getUsage());
            }
            String argument = Parser.removeFirstArgument(userInput);
            return runCommand(command, argument);
        } catch (InvalidCommandException ive) {
            Message.printWithSpacers(ive.getMessage());
        }
        return true;
    }
}
