package duke.task;

public class Parser {

    public String[] getInputAsWordsArray(String userInput) {
        return userInput.split(" ");
    }

    public String getCommand(String[] inputWords) {
        return inputWords[0];
    }
}
