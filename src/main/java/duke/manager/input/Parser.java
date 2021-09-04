package duke.manager.input;

public class Parser {
    private String[] arguments;
    private String input;
    private boolean isMoreThanTwoWords;

    public String[] getArguments() {
        return arguments;
    }

    public String getInput() {
        return input;
    }

    public boolean isMoreThanTwoWords() {
        return isMoreThanTwoWords;
    }

    public Parser(String input) {
        if (isMoreThanTwoWords(input)) {
            arguments = input.trim().split(" ", 2);
        } else {
            arguments = new String[1];
            arguments[0] = input.trim();
        }
        this.input = input.trim();
    }

    public boolean isMoreThanTwoWords (String input) {
        if (input.trim().indexOf(" ") > -1) {
            this.isMoreThanTwoWords = true;
            return true;
        }
        this.isMoreThanTwoWords = false;
        return false;
    }

    public String getFirstWord () {
        return arguments[0].trim();
    }
}
