package Commands;

import DukeClasses.Storage;
import DukeClasses.TaskList;
import DukeClasses.Ui;
import Exceptions.EmptyTaskException;
import Exceptions.InvalidCommandException;

import java.io.IOException;

public class FindCommand extends Command {
    protected String filter;

    public FindCommand(String filter) {
        this.filter = filter;
    }

    @Override
    public void executeCommand(TaskList tasks, Ui ui, Storage storage) throws EmptyTaskException, IOException, InvalidCommandException {
        System.out.println("____________________________________________________________");
        System.out.println("Here are the matching tasks in your list:");

        for (int i = 1; i <= tasks.getList().size(); i++) {
            String task = tasks.getList().get(i - 1).getDescription();
            if (task.contains(filter)) {
                System.out.println(i + ". " + tasks.getList().get(i - 1));
            }
        }
        System.out.println("____________________________________________________________\n");
    }
}
