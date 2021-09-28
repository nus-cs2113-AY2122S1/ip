package duke.command;

import duke.tasklist.TaskList;

public class DeleteCommand extends Command {
    public DeleteCommand(TaskList taskList, String userInput) {
        taskList.deleteTask(userInput);
    }
}
