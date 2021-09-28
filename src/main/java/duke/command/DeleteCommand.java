package duke.command;

import duke.task.Task;
import duke.tasklist.TaskList;

public class DeleteCommand extends Command {
    /**
     * Delete task from task list
     *
     * @param taskList list of task
     * @param userInput user input
     */
    public DeleteCommand(TaskList taskList, String userInput) {
        try {
            int index = Integer.parseInt(userInput.split(" ")[1]) - 1;
            Task removedTask = taskList.getTasks().get(index);
            taskList.getTasks().remove(index);
            System.out.println("Noted. I've removed this task:\n  " + removedTask.toString());
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Please enter index of task to be removed");
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            System.out.println("Please enter index within range 1 to " + taskList.getTasks().size());
        }
    }
}
