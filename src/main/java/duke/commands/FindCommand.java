package duke.commands;

import duke.data.TaskList;
import duke.exception.DukeException;
import duke.storage.Storage;
import duke.ui.Ui;

public class FindCommand extends Command {
    private final String keyword;
    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all tasks whose descriptions contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD\n"
            + "⮞ Example: " + COMMAND_WORD + " book";

    public FindCommand(String keyword){
        this.keyword = keyword;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        TaskList matchingTasks = tasks.search(this.keyword);

        if (matchingTasks.getSize() == 0) {
            System.out.println("There is no matching task.");
        } else {
            System.out.println("Here are the matching tasks in your list:");
            matchingTasks.printList();
        }
    }

}
