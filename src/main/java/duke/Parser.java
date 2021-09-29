package duke;

public class Parser {
    private static Ui ui;

    public Parser() {
        ui = new Ui();
    }

    public static String[] parse(String fullCommand) throws ArrayIndexOutOfBoundsException {
        String[] words = fullCommand.split(" ", 2);
        String commandWord = words[0].toLowerCase();

        switch (commandWord) {
        case "list":
        case "bye":
            return words;
        case "done":
        case "delete":
            String[] parsedFullCommand = fullCommand.split(" ");
            return parsedFullCommand;
        case "todo":
            String description = words[1];
            return new String[]{commandWord, description};
        case "deadline":
            description = words[1].split(" /by ")[0];
            String by = words[1].split(" /by ")[1];
            return new String[]{commandWord, description, by};
        case "event":
            description = words[1].split(" /at ")[0];
            String at = words[1].split(" /at ")[1];
            return new String[]{commandWord, description, at};
        default:
            return new String[]{"wrong", "input"};
        }
    }
}
