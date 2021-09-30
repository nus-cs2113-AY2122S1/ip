package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class FindCommand extends Command {

    private final Pattern findRegex;

    /**
     * Setup find command with proper regex according to user input
     *
     * @param userInput string given by user to be checked for in task description
     */
    public FindCommand(String userInput) {
        this.findRegex = Pattern.compile(".*" + userInput + ".*");
    }

    /**
     * A find command execute method which will search for task description in tasks that match the Pattern findRegex
     * which will then display it to user using ui. As long as there is any trace of the word given, the task will be
     * returned to be shown. For example: "find test" will find all instance of the description test which will also
     * include tester, test, mock test.
     *
     * @param tasks   TaskList Class which stores all tasks related information such as task array and handle all task
     *                related operations
     * @param ui      UI class which handles all user interactions
     * @param storage Storage class which allows all loading and saving of task information
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> taskList = tasks.getTaskList();
        ArrayList<Task> taskThatMatchFind = new ArrayList<Task>();
        for (Task task : taskList) {
            boolean foundDescriptionMatch = findRegex.matcher(task.getDescription()).matches();
            if (foundDescriptionMatch) {
                taskThatMatchFind.add(task);
            }
        }
        ui.printFindTaskMessage(taskThatMatchFind);

    }

}
