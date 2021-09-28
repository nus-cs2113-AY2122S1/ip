package duke.command;

import duke.tasklist.TaskList;

public class DoneCommand extends Command {
    /**
     * Mark a task as done
     *
     * @param taskList list of task
     * @param userInput user input
     */
    public DoneCommand(TaskList taskList, String userInput) {
        taskList.doneTask(userInput);
    }
}
