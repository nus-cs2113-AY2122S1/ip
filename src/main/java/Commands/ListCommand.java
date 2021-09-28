package Commands;

import DukeClasses.Storage;
import DukeClasses.TaskList;
import DukeClasses.Ui;

public class ListCommand extends Command {

    @Override
    public void executeCommand(TaskList tasks, Ui ui, Storage storage) {
        System.out.println("____________________________________________________________");
        System.out.println("Here are your remaining tasks:");

        for (int i = 1; i <= tasks.getList().size(); i++) {
            System.out.println(i + ". " + tasks.getList().get(i - 1)
            );
        }
        System.out.println("____________________________________________________________\n");

    }
}
