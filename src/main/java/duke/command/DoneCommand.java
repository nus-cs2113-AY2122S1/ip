package duke.command;

import duke.tasklist.TaskList;

public class DoneCommand extends Command {
    public DoneCommand(TaskList taskList, String userInput) {
        taskList.doneTask(userInput);
    }
}
