package triss;

public class Parser {

    public Parser() {
        // Parser has no dependencies to be initialised
    }

    /**
     * Parse the user input and return the word in the index the user wants.
     * @param userInput The user input to be parsed.
     * @param i The index of the word in the user input to be returned.
     * @return Parsed string from user input.
     */
    public String parseUserInput(String userInput, int i) {
        return userInput.split(" ")[i];
    }

    public String getDeadlineOrTiming(String userInput) {
        return userInput.substring(userInput.indexOf("/") + 1).trim();
    }

    public String getTaskName(String userInput, int lengthOfCommandWord) {
        return userInput.substring(lengthOfCommandWord, userInput.indexOf("/")).trim();
    }
}
