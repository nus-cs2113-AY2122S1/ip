package duke.control.command;

import duke.control.TaskList;

public class ListCommand extends Command {

    @Override
    public void executeCommand(TaskList list, String input) {
        list.printList();
    }
}
