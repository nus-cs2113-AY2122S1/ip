package duke.command;

import duke.tasklist.TaskList;

public class DeleteCommand extends Command {
    /**
     * Delete task from task list
     *
     * @param taskList list of task
     * @param userInput user input
     */
    public DeleteCommand(TaskList taskList, String userInput) {
        taskList.deleteTask(userInput);
    }
}
