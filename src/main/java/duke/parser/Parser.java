package duke.parser;

import duke.exception.EmptyArgumentException;
import duke.exception.EmptyParameterException;

/**
 * Methods that deal with making sense of the user input.
 * Note: The "command" is the first word of the input. The "content" is everything
 * beyond the first word.
 */
public class Parser {

    /**
     * Extracts the date from the content of an event input (everything after "/at")
     *
     * @param content the content of the event input
     * @return the date of the event
     * @throws EmptyParameterException when there is no date given for the event
     */
    public String extractAtFromEvent(String content) throws EmptyParameterException {
        int positionOfSeparator = content.trim().indexOf("/at");
        if ((positionOfSeparator + 3) >= content.length()) {
            throw new EmptyParameterException();
        }
        return content.substring(positionOfSeparator + 3).trim();
    }

    /**
     * Extracts the description from the content of an event input (everything before "/at")
     *
     * @param content the content of the event input
     * @return the description of the event
     * @throws EmptyParameterException when there is no description for the event
     */
    public String extractDescriptionFromEvent(String content) throws EmptyParameterException {
        int positionOfSeparator = content.trim().indexOf("/at");
        if (positionOfSeparator <= 0) {
            throw new EmptyParameterException();
        }
        return content.substring(0, positionOfSeparator - 1).trim();
    }

    /**
     * Extracts the deadline from the content of a deadline input (everything after "/by")
     *
     * @param content the content of the deadline input
     * @return the deadline of the deadline
     * @throws EmptyParameterException when there is no date given for the deadline
     */
    public String extractByFromDeadline(String content) throws EmptyParameterException {
        int positionOfSeparator = content.trim().indexOf("/by");
        if ((positionOfSeparator + 3) >= content.length()) {
            throw new EmptyParameterException();
        }
        return content.substring(positionOfSeparator + 3).trim();
    }

    /**
     * Extracts the description from the content of a deadline input (everything before "/by")
     *
     * @param content the content of the deadline input
     * @return the description of the deadline
     * @throws EmptyParameterException when there is no description for the deadline
     */
    public String extractDescriptionFromDeadline(String content) throws EmptyParameterException {
        int positionOfSeparator = content.trim().indexOf("/by");
        if (positionOfSeparator <= 0) {
            throw new EmptyParameterException();
        }
        return content.substring(0, positionOfSeparator - 1).trim();
    }

    /**
     * Extracts the content from the input string
     *
     * @param input the full input string given by the user
     * @return the content of the input stream
     * @throws EmptyArgumentException when the content of the input is empty
     */
    public String extractContent(String input) throws EmptyArgumentException {
        int firstSpacePosition = input.trim().indexOf(" ");
        if (firstSpacePosition < 0) {
            throw new EmptyArgumentException();
        }
        return input.substring(firstSpacePosition + 1).trim();
    }

    /**
     * Extracts the command from the input string
     *
     * @param input the full input string given by the user
     * @return the command of the input string
     */
    public String extractCommand(String input) {
        String[] words = input.trim().split(" ");
        return words[0].trim();
    }

    /**
     * Extracts the integer from the input string and deducts 1 from it to correspond
     * to its index in an array
     *
     * @param input the full input string given by the user
     * @return the array index corresponding to the content of the input
     * @throws NumberFormatException when the content of the input is not a number
     * @throws EmptyArgumentException when the content of the input is empty
     */
    public int extractIndex(String input) throws NumberFormatException, EmptyArgumentException {
        String content = extractContent(input);
        return Integer.parseInt(content) - 1;
    }
}
