package duke;

import duke.exception.InvalidCommandException;
import duke.task.Task;
import duke.task.TaskManager;

class Command {

    private enum Commands {
        BYE("bye"),
        LIST("list"),
        DONE("done <task number>"),
        DELETE("delete <task number>");

        private final String usage;
        private static final String REGEX_FORMAT = "(?i)%s" + Message.WHITESPACE_REGEX + "\\d+$";

        private Commands(String usage) {
            this.usage = usage;
        }

        private String getUsage() {
            return "Wrong argument(s). Usage: " + usage;
        }

        private String getRegex() {
            return String.format(REGEX_FORMAT, this);
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
            Commands command = Commands.valueOf(userInputSplit[0].toUpperCase());
            switch (command) {
            case BYE:
                //Fallthrough
            case LIST:
                if (userInputSplit.length != 1) {
                    throw new InvalidCommandException(command.getUsage());
                }
                if (command == Commands.BYE) {
                    return false;
                }
                TaskManager.printTasks();
                break;
            case DONE:
                //Fallthrough
            case DELETE:
                if (!userInput.matches(command.getRegex()) || userInputSplit.length != 2) {
                    throw new InvalidCommandException(command.getUsage());
                }
                int id = Integer.parseInt(userInputSplit[1]);
                //id entered with index starting from '1' instead of '0'
                if (command == Commands.DONE) {
                    TaskManager.taskDone(id - 1);
                } else {
                    TaskManager.deleteTask(id - 1);
                }
                break;
            }
        } catch (InvalidCommandException ive) {
            Message.printWithSpacers(ive.getMessage());
        }
        return true;
    }
}
