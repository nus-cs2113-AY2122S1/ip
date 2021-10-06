package duke.commands;

import duke.components.Storage;
import duke.components.TaskList;
import duke.components.Ui;
import duke.task.Task;

import static duke.constants.Messages.HORIZONTAL_LINE;
import static duke.constants.Messages.LINE_PREFIX;
import static duke.constants.Messages.MESSAGE_FOUND_MATCH;
import static duke.constants.Messages.MESSAGE_NOT_FOUND_MATCH;

/**
 * A representation of the command for finding a task.
 */
public class FindCommand extends Command {

    String keyword;

    /**
     * Class constructor specifying the keyword.
     *
     * @param keyword The keyword that is used to find relevant tasks.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the find command.
     *
     * @param tasks The ArrayList of tasks.
     * @param ui The component of Duke that deals with the interaction with the user.
     * @param storage The component of Duke that deals with loading tasks from the file and saving tasks in the file.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        System.out.println(HORIZONTAL_LINE);
        int numberOfMatches = 0;
        boolean hasMatch = false;
        for (int i = 0; i < tasks.getNumberOfTasks(); i++) {
            Task task = tasks.getTaskByIndex(i);
            if (task.getDescription().contains(this.keyword)) {
                if (!hasMatch) {
                    System.out.println(MESSAGE_FOUND_MATCH);
                    hasMatch = true;
                }
                numberOfMatches++;
                System.out.println(LINE_PREFIX + numberOfMatches + ". " + task);
            }
        }

        if (!hasMatch) {
            System.out.println(MESSAGE_NOT_FOUND_MATCH);
        }
    }
}
