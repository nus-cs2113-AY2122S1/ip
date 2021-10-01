/**
 * This class handles the input from users by breaking it into command and content.
 */
public class Parser {

    public Parser() {}

    /**
     * Process first word of input line given by the user.
     * The first word is the type of command that the user wants to give Duke.
     *
     * @param line input from user
     * @return the type of command the user is giving, in String form
     */

    public String findCommand(String line) {
        int spaceIndex = line.indexOf(" ");
        return spaceIndex == -1 ? line : line.substring(0, spaceIndex);
    }

    /**
     * Process rest of input line given by the user.
     * The rest of the input line contains specifications to the above command, if any.
     *
     * @param line input from user
     * @return the specifications the user is giving, in String form
     */

    public String findContent(String line) {
        int spaceIndex = line.indexOf(" ");
        return spaceIndex == -1 ? line : line.substring(spaceIndex);
    }

}
