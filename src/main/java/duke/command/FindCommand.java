package duke.command;

import duke.tasklist.TaskList;

public class FindCommand {
    public FindCommand(TaskList taskList, String userInput) {
        taskList.findTask(userInput);
    }
}
