package duke.manager.input;

/**
 * <h1>Parser</h1>
 * This class helps to parse the user input into arguments. It checks if the input String has more than 1 word before
 * parsing the input into arguments. <code>argument</code> has length 1 if input only has 1 word and length 2 if
 * input has more than 1 word.
 */
public class Parser {

    private String[] arguments;
    private String input;
    private boolean isMoreThanOneWord;

    public String[] getArguments() {
        return arguments;
    }

    public String getInput() {
        return input;
    }

    public boolean isMoreThanOneWord() {
        return isMoreThanOneWord;
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

    public boolean isMoreThanTwoWords(String input) {
        if (input.trim().indexOf(" ") > -1) {
            this.isMoreThanOneWord = true;
            return true;
        }
        this.isMoreThanOneWord = false;
        return false;
    }

    public String getFirstWord() {
        return arguments[0].trim();
    }
}
