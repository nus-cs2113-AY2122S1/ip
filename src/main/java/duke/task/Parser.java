package duke.task;

public class Parser {

    /**
     * Splits a user-input string into an array of words.
     * The initial string is separated at blank spaces.
     *
     * @param userInput String input by the user.
     * @return Array of individual strings.
     */
    public String[] getInputAsWordsArray(String userInput) {
        return userInput.split(" ");
    }

    /**
     * Gets the command word (ie. first string) from the user's input string array.
     *
     * @param inputWords Array of strings obtained from user's input.
     * @return The first string in the array.
     */
    public String getCommand(String[] inputWords) {
        return inputWords[0];
    }
}
