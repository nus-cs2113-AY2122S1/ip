package duke.commands;

import duke.tasks.TaskList;

public class ListCommand extends Command {
    public ListCommand(TaskList taskList) {
        super(taskList, "");
    }

    @Override
    public CommandOutput execute() throws Exception {
        String response = taskList.printList();
        return new CommandOutput(response, false);
    }
}
