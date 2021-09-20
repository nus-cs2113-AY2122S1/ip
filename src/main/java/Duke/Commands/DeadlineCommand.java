package Duke.Commands;

import Duke.DukeException;
import Duke.Task.Deadline;
import Duke.Task.Task;
import Duke.UI;

import java.util.ArrayList;

public class DeadlineCommand extends Command {
    public static final String COMMAND_WORD = "deadline";
    private final String taskName;
    private final String dueDate;

    public DeadlineCommand(String input) throws DukeException {
        int slashIndex = input.indexOf('/');
        this.taskName = getDeadlineTaskName(input, slashIndex);
        this.dueDate = getDueDate(input, slashIndex);
    }

    private static String getDeadlineTaskName(String input, int slashIndex) throws DukeException {
        final int DEADLINE_WORD_LENGTH = 9;
        int taskNameLastIndex = slashIndex - 1; //the last index of the task name
        //if the slash is not present in the input
        if (slashIndex == -1) {
            throw new DukeException("☹ OOPS!!! The description of an deadline task requires a task name" + System.lineSeparator()
                    + "\tfollowed by a front slash, and then a specific date/time after." + System.lineSeparator()
                    + "\ti.e. deadline submit report /by 11-10-2019 5pm");
        } else if (taskNameLastIndex <= DEADLINE_WORD_LENGTH) { //if the slash exists but the task name is empty
            throw new DukeException("☹ OOPS!!! The name of an deadline task cannot be empty.");
        }
        String taskName = input.substring(DEADLINE_WORD_LENGTH, taskNameLastIndex);
        //if taskName are just filled with whitespaces
        if (taskName.replace(" ", "").isEmpty()) {
            throw new DukeException("☹ OOPS!!! The name of a deadline task cannot be empty.");
        }
        return taskName;
    }

    @Override
    public void execute(ArrayList<Task> tasksList) {
        tasksList.add(new Deadline(taskName, dueDate));
        UI.printNewTaskMsg(tasksList);
    }

    @Override
    public void executeFromFile(ArrayList<Task> tasksList) {
        tasksList.add(new Deadline(taskName, dueDate));
    }
}
