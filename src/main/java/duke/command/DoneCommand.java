package duke.command;

import duke.task.Task;
import duke.tasklist.TaskList;

public class DoneCommand extends Command {
    /**
     * Mark a task as done
     *
     * @param taskList list of task
     * @param userInput user input
     */
    public DoneCommand(TaskList taskList, String userInput) {
        try {
            int index = Integer.parseInt(userInput.split(" ")[1]) - 1;
            Task task = taskList.getTasks().get(index);
            task.setDone();
            System.out.println("Nice! I've marked this task as done:\n  " + task);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Please enter index of task done");
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            System.out.println("Please enter index within range 1 to " + taskList.getTasks().size());
        }
    }
}
