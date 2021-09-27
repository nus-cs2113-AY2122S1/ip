package duke.command;

import duke.Parser;

enum Command {
    BYE("bye", 1, "(?i)%s"),
    LIST("list", 1, "(?i)%s"),
    DONE("done <task number>", 2, "(?i)%s\\s+\\d+$"),
    DELETE("delete <task number>", 2, "(?i)%s\\s+\\d+$"),
    FIND("find <description>", 2, "(?i)%s\\s+\\S+$");

    private static final String USAGE_MESSAGE = "Wrong argument(s). Usage: ";

    private final String USAGE;
    private final int NUMBER_OF_ARGS;
    private final String REGEX;

    Command(String usage, int numberOfArgs, String regex) {
        USAGE = usage;
        NUMBER_OF_ARGS = numberOfArgs;
        REGEX = regex;
    }

    String getUsage() {
        return USAGE_MESSAGE + USAGE;
    }

    static Command getCommand(String commandString){
        return valueOf(commandString.toUpperCase());
    }

    @Override
    public String toString(){
        return super.toString().toLowerCase();
    }

    private boolean matchesRegex(String userInput) {
        return userInput.matches(String.format(REGEX, toString()));
    }

    static boolean contains(String userInput) {
        userInput = userInput.toUpperCase();
        for (Command command : Command.values()) {
            if (command.name().equals(userInput)) {
                return true;
            }
        }
        return false;
    }

    boolean isValid(String userInput){
        String[] userInputSplit = Parser.splitWhitespace(userInput);
        return userInputSplit.length == NUMBER_OF_ARGS && matchesRegex(userInput);
    }
}
