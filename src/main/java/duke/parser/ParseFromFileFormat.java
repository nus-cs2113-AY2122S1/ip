package duke.parser;

public class ParseFromFileFormat extends Parser {
    private static final String COMMAND_TODO = "todo";
    private static final String COMMAND_DEADLINE = "deadline";
    private static final String COMMAND_EVENT = "event";
    private static final String ICON_TODO = "T";
    private static final String ICON_DEADLINE = "D";
    private static final String ICON_EVENT = "E";
    private static final String ICON_COMPLETEDTASK = "X";

    public ParseFromFileFormat(String input) {
        handleFileInputs(input);
    }

    private void handleFileInputs(String input) {
        String[] inputSubstrings = input.split("\\|");

        String type = inputSubstrings[0].strip();
        switch (type) {
        case ICON_TODO:
            command = COMMAND_TODO;
            break;
        case ICON_DEADLINE:
            command = COMMAND_DEADLINE;
            formattedTimeField = inputSubstrings[3].strip();
            break;
        case ICON_EVENT:
            command = COMMAND_EVENT;
            formattedTimeField = inputSubstrings[3].strip();
            break;
        }
        done = inputSubstrings[1].strip().equals(ICON_COMPLETEDTASK);
        description = inputSubstrings[2].strip();
    }
}
