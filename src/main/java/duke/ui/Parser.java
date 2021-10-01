package duke.ui;
import duke.command.Command;
import duke.task.TaskList;

public class Parser {
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
            case FIND:
                TaskList.findTaskWithSubstring(userInput);
                return true;
            case BYE:
                return false;
            default:
                return true;
            }
        } catch (NullPointerException e){
            return true;
        }
    }

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
