package duke.command;

import duke.TaskList;

public class FindCommand implements Command {
    private static final CommandType type = CommandType.FIND;
    private final String keyword;

    /**
     * Find command constructor
     *
     * @param keyword Search term to search through task titles
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void run(boolean printMessage) {
        TaskList.listMatchingTasks(keyword);
    }

    @Override
    public CommandType getType() {
        return type;
    }
}
