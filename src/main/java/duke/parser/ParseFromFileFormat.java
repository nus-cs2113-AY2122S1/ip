package duke.parser;

public class ParseFromFileFormat extends Parser {
    public ParseFromFileFormat(String input) {
        handleFileInputs(input);
    }

    private void handleFileInputs(String input) {
        String[] inputSubstrings = input.split("\\|");

        String type = inputSubstrings[0].strip();
        switch (type) {
        case "T":
            command = "todo";
            break;
        case "D":
            command = "deadline";
            formattedTimeField = inputSubstrings[3].strip();
            break;
        case "E":
            command = "event";
            formattedTimeField = inputSubstrings[3].strip();
            break;
        }
        done = inputSubstrings[1].strip().equals("X");
        description = inputSubstrings[2].strip();
    }
}
