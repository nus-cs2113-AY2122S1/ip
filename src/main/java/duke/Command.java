package duke;

import duke.exception.InvalidCommandException;
import duke.task.Task;
import duke.task.TaskManager;

class Command {

    private enum Commands {
        BYE("bye"),
        LIST("list"),
        DONE("done <task number>");

        private final String usage;

        private Commands(String usage) {
            this.usage = usage;
        }

        private String getUsage() {
            return "Wrong argument(s). Usage: " + usage;
        }

        private static boolean contains(String userInput) {
            userInput = userInput.toUpperCase();
            for (Commands command : Commands.values()) {
                if (command.name().equals(userInput)) {
                    return true;
                }
            }
            return false;
        }
    }

    private static final String DONE_REGEX = "(?i)" + Commands.DONE + Message.WHITESPACE_REGEX + "\\d+$";

    static boolean handleCommand(String userInput) {
        if (userInput.matches(Task.Types.getTypesRegex())) {
            TaskManager.newTask(userInput);
            return true;
        }
        String[] userInputSplit = Message.splitWhitespace(userInput);
        try {
            if (!Commands.contains(userInputSplit[0])) {
                throw new InvalidCommandException("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            switch (Commands.valueOf(userInputSplit[0])) {
            case BYE:
                if (userInputSplit.length != 1) {
                    throw new InvalidCommandException(Commands.BYE.getUsage());
                }
                return false;
                //Fallthrough
            case LIST:
                if (userInputSplit.length != 1) {
                    throw new InvalidCommandException(Commands.LIST.getUsage());
                }
                TaskManager.printTasks();
                break;
            case DONE:
                if (!userInput.matches(Command.DONE_REGEX) || userInputSplit.length != 2) {
                    throw new InvalidCommandException(Commands.DONE.getUsage());
                }
                int id = Integer.parseInt(userInputSplit[1]);
                //id entered with index starting from '1' instead of '0'
                TaskManager.taskDone(id - 1);
                break;
            }
        } catch (InvalidCommandException ive) {
            Message.printWithSpacers(ive.getMessage());
        }
        return true;
    }
}
