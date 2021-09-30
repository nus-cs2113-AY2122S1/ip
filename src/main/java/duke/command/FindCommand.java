package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class FindCommand extends Command{

    private final Pattern findRegax;

    /**
     * Find any task that contains the word that was given by user. As long as there is any trace of the word given,
     * the task will be returned to be shown. For example: "find test" will find all instance of the description test
     * which will also include tester, test, mock test.
     *
     * @param userInput string given by user to be checked for in task description
     */
    public FindCommand(String userInput) {
        this.findRegax = Pattern.compile(".*"+userInput+".*");
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> taskList = tasks.getTaskList();
        ArrayList<Task> taskThatMatchFind = new ArrayList<Task>();
        for (Task task : taskList){
            boolean foundDescriptionMatch = findRegax.matcher(task.getDescription()).matches();
            if (foundDescriptionMatch){
                taskThatMatchFind.add(task);
            }
        }
        ui.printFindTaskMessage(taskThatMatchFind);

    }

}
