import exceptions.DukeException;
import exceptions.EmptyTimeException;

public class Parser {

    /**
     * Extract command specified by user, which is the first word of the string
     *
     * @param userInput raw String input from user
     * @return command specified by user
     */
    public static String parseExtractCommand(String userInput) {
        String[] params = userInput.split(" ", 2);
        return params[0];
    }

    /**
     * Extract index from user string
     * Assuming position starts from 1 and index starts from 0,
     * index = (position of task on ArrayList) - 1;
     *
     * @param userInput raw String input from user
     * @return index of task
     * @throws DukeException the exception thrown when user did not include p
     * @throws IndexOutOfBoundsException the exception thrown when user enter a number
     * that is out of bound of ArrayList<Task> tasks
     * @throws NumberFormatException the exception thrown when user input a non-numeric value
     */
    public static int parseExtractIndex(String userInput) throws DukeException, IndexOutOfBoundsException, NumberFormatException{
        String[] params = userInput.split(" ", 2);
        if (params.length < 2) {
            throw new DukeException();
        }
        String position = params[1];
        int index = Integer.parseInt(position) - 1;
        if (index < 0 || index >= TaskManager.tasks.size()) {
            throw new IndexOutOfBoundsException();
        }
        return index;
    }

    /**
     * Extract the description of tasks or query by user, without the command
     *
     * @param userInput raw String input from user
     * @return description or query
     * @throws DukeException the exception thrown when user left the description empty
     */
    public static String parseExtractString(String userInput) throws DukeException {
        String[] params = userInput.split(" ", 2);
        if (params.length < 2) {
            throw new DukeException();
        }
        return params[1];
    }

    /**
     * Extract the description and time of deadline/event
     *
     * @param userInput raw String input from user
     * @return an Array including the description and time
     * @throws EmptyTimeException the exception thrown when user left the date/time empty
     */
    public static String[] parseExtractInfoAndTime(String userInput) throws EmptyTimeException {
        String[] separateTime = userInput.split("/", 2);
        if (separateTime.length<2) {
            throw new EmptyTimeException();
        }
        String description = separateTime[0];
        String[]separateAtBy = separateTime[1].split(" ", 2);
        if (separateAtBy.length<2) {
            throw new EmptyTimeException();
        }
        String time = separateAtBy[1];
        return new String[]{description, time};
    }

}
