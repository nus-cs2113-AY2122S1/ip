package duke.parser;

import duke.exception.EmptyArgumentException;
import duke.exception.EmptyParameterException;
import duke.exception.MultipleKeywordsException;
import duke.task.TaskTimeManager;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

/**
 * Methods that deal with making sense of the user input.
 * Note: The "command" is the first word of the input. The "content" is everything
 * beyond the first word.
 */
public class Parser {

    private static final TaskTimeManager taskTimeManager = new TaskTimeManager();

    /**
     * Extracts the date from the content of an event input (everything after "/at")
     *
     * @param content the content of the event input
     * @return the date of the event
     * @throws EmptyParameterException when there is no date given for the event
     */
    public LocalDateTime extractAtFromEvent(String content) throws EmptyParameterException {
        int positionOfSeparator = content.trim().indexOf("/at");
        if ((positionOfSeparator + 3) >= content.length()) {
            throw new EmptyParameterException();
        }
        return taskTimeManager.parseDateTime(content.substring(positionOfSeparator + 3).trim());
    }

    /**
     * Extracts the description from the content of an event input (everything before "/at")
     *
     * @param content the content of the event input
     * @return the description of the event
     * @throws EmptyParameterException when there is no description for the event
     */
    public String extractDescriptionFromEvent(String content) throws EmptyParameterException, DateTimeParseException {
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
    public LocalDateTime extractByFromDeadline(String content) throws EmptyParameterException, DateTimeParseException {
        int positionOfSeparator = content.trim().indexOf("/by");
        if ((positionOfSeparator + 3) >= content.length()) {
            throw new EmptyParameterException();
        }
        return taskTimeManager.parseDateTime(content.substring(positionOfSeparator + 3).trim());
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
     * @param input the input string
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
     * Extracts the command from the input string (the first word)
     *
     * @param input the input string
     * @return the command of the input string
     */
    public String extractCommand(String input) {
        String[] words = input.trim().split(" ");
        return words[0].trim();
    }

    /**
     * Extracts the integer from the input string and then deducts 1 from it to
     * represent its index in an array. Then returns this said index.
     *
     * @param input the input string given
     * @return the index in an array in which the number in the input represents
     * @throws NumberFormatException when the content of the input is not a number
     * @throws EmptyArgumentException when the content of the input is empty
     */
    public int extractIndex(String input) throws NumberFormatException, EmptyArgumentException {
        String content = extractContent(input);
        return Integer.parseInt(content) - 1;
    }

    /**
     * Extracts the keyword from the input string
     *
     * @param input the input string containing the command and the keyword
     * @return the keyword of the input string
     * @throws MultipleKeywordsException when there are more than 1 keywords given
     * @throws EmptyArgumentException when there is no keyword given
     */
    public String extractKeyword(String input) throws MultipleKeywordsException, EmptyArgumentException {
        String content = extractContent(input);
        String[] keyWords = content.split(" ");
        //Ensures that there is only 1 keyword given
        if (keyWords.length > 1) {
            throw new MultipleKeywordsException();
        }
        return keyWords[0];
    }

    /**
     * Extracts the integer from the input string and returns it
     *
     * @param input the input string given
     * @return the integer in the input string
     * @throws NumberFormatException when the content of the input is not a number
     * @throws EmptyArgumentException when the content of the input is empty
     */
    public int extractNumber(String input) throws NumberFormatException, EmptyArgumentException {
        String content = extractContent(input);
        return Integer.parseInt(content);
    }
}
