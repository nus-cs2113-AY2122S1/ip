package Duke.Commands;

import Duke.DukeException;

public class FindCommand extends Command {
    public static final String COMMAND_WORD = "find";
    public String searchParams;

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

    private String getSearchParams(String input) throws DukeException {
        int spaceIndex = input.indexOf(' ');
        if (spaceIndex == -1) {
            throw new DukeException("☹ OOPS!!! The search parameters of a find command cannot be empty.");
        }
        return input.substring(spaceIndex + 1);
    }

    public void execute() {
        taskList.searchList(searchParams);
    }
}
