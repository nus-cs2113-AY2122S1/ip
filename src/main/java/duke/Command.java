package duke;

import duke.exception.InvalidCommandException;
import duke.task.Task;
import duke.task.TaskManager;

class Command {

    private enum Commands {
        BYE("bye", 1, "(?i)%s"),
        LIST("list", 1, "(?i)%s"),
        DONE("done <task number>", 2, "(?i)%s\\s+\\d+$"),
        DELETE("delete <task number>", 2, "(?i)%s\\s+\\d+$"),
        FIND("find <description>", 2, "(?i)%s\\s+\\S+$");

        private final String USAGE;
        private final int ARGS;
        private final String REGEX;

        Commands(String usage, int args, String regex) {
            USAGE = usage;
            ARGS = args;
            REGEX = regex;
        }

        private String getUsage() {
            return "Wrong argument(s). Usage: " + USAGE;
        }

        private static Commands getCommand(String commandString){
            return valueOf(commandString.toUpperCase());
        }

        @Override
        public String toString(){
            return super.toString().toLowerCase();
        }

        private boolean matchesRegex(String userInput) {
            return userInput.matches(String.format(REGEX, toString()));
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

        private boolean isValidCommand(String userInput){
            String[] userInputSplit = Parser.splitWhitespace(userInput);
            return userInputSplit.length == ARGS && matchesRegex(userInput);
        }
    }

    static boolean handleCommand(String userInput) {
        if (userInput.matches(Task.Type.getTypesRegex())) {
            TaskManager.newTask(userInput);
            return true;
        }
        String[] userInputSplit = Parser.splitWhitespace(userInput);
        try {
            if (!Commands.contains(userInputSplit[0])) {
                throw new InvalidCommandException("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            Commands command = Commands.getCommand(userInputSplit[0]);
            if (!command.isValidCommand(userInput)) {
                throw new InvalidCommandException(command.getUsage());
            }
            switch (command) {
            case BYE:
                return false;
            case LIST:
                TaskManager.printTasks();
                break;
            case DONE:
                TaskManager.taskDone(Integer.parseInt(userInputSplit[1]) - 1);
                break;
            case DELETE:
                TaskManager.deleteTask(Integer.parseInt(userInputSplit[1]) - 1);
                break;
            case FIND:
                TaskManager.findTasks(userInputSplit[1]);
                break;
            }
        } catch (InvalidCommandException ive) {
            Message.printWithSpacers(ive.getMessage());
        }
        return true;
    }
}
