package Duke.Commands;

import Duke.DukeException;

public class FindCommand extends Command {
    public static final String COMMAND_WORD = "find";
    public String searchParams;

    /**
     * Initialises the search parameters from the input entered by the user.
     *
     * @param input The entire line of command entered by the user.
     * @throws DukeException If the search parameters is empty.
     */
    public FindCommand(String input) throws DukeException {
        try {
            searchParams = getSearchParams(input);
            if (searchParams.replace(" ", "").isEmpty()) {
                throw new DukeException("☹ OOPS!!! The search parameters of a find command cannot be empty.");
            }
        } catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException("☹ OOPS!!! The search parameters of a find command cannot be empty.");
        }
    }

    /**
     *  Returns the search parameters given the input entered by the user.
     *
     * @param input The entire line of command entered by the user.
     * @return Returns the search parameters in String format.
     * @throws DukeException If search parameters is empty.
     */
    private String getSearchParams(String input) throws DukeException {
        int spaceIndex = input.indexOf(' ');
        if (spaceIndex == -1) {
            throw new DukeException("☹ OOPS!!! The search parameters of a find command cannot be empty.");
        }
        return input.substring(spaceIndex + 1);
    }

    /**
     * Search the list given the search parameters
     * and prints all tasks that satisfy the search parameters.
     */
    public void execute() {
        taskList.searchList(searchParams);
    }
}
