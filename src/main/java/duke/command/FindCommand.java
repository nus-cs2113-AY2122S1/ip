package duke.command;

import duke.tasklist.TaskList;

public class FindCommand {
    /**
     * Find list of tasks with matching words
     *
     * @param taskList list of task
     * @param userInput user input
     */
    public FindCommand(TaskList taskList, String userInput) {
        taskList.findTask(userInput);
    }
}
