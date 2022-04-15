package duke.commands;
import duke.TaskList;
import duke.Ui;
import duke.tasks.Task;

public class ListCommand extends Command {

    /**
     * Command when user wants to list all Tasks (e.g. Todo, Deadline, Event) in the TaskList.
     * When executed, it will return a successful list message, and show all Tasks in the TaskList.
     */

    public ListCommand(){
    }

    public void execute(TaskList taskList, Ui ui) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i <= taskList.size(); i++) {
            Task currTask = taskList.get(i - 1);
            System.out.println(i + ". " + currTask);
        }
    }

    public boolean isExit(){
        return false;
    }
}
