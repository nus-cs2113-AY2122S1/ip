package duke;

import duke.exception.InvalidCommandException;
import duke.task.Task;
import duke.task.TaskManager;

class Command {

    private enum Commands {
        BYE("bye", 1),
        LIST("list", 1),
        DONE("done <task number>", 2),
        DELETE("delete <task number>", 2);

        private final String usage;
        private final int ARGS;

        private static final String REGEX_INT_ARG = "(?i)%s" + Message.WHITESPACE_REGEX + "\\d+$";

        Commands(String usage, int args) {
            this.usage = usage;
            ARGS = args;
        }

        private String getUsage() {
            return "Wrong argument(s). Usage: " + usage;
        }

        private String getRegexIntArg() {
            return String.format(REGEX_INT_ARG, this);
        }

        private boolean matchesRegex(String userInput) {
            if (this == DONE || this == DELETE) {
                return userInput.matches(getRegexIntArg());
            }
            //No need to check regex for BYE or LIST
            return true;
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
            if (userInputSplit.length != command.ARGS || !command.matchesRegex(userInput)) {
                throw new InvalidCommandException(command.getUsage());
            }
            switch (command) {
            case BYE:
                return false;
                //Fallthrough
            case LIST:
                TaskManager.printTasks();
                break;
            case DONE:
                TaskManager.taskDone(Integer.parseInt(userInputSplit[1]) - 1);
                break;
            case DELETE:
                TaskManager.deleteTask(Integer.parseInt(userInputSplit[1]) - 1);
                break;
            }
        } catch (InvalidCommandException ive) {
            Message.printWithSpacers(ive.getMessage());
        }
        return true;
    }
}
