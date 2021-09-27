package Duke.Commands;

import Duke.DukeException;
import Duke.Task.Deadline;
import Duke.UI;


public class DeadlineCommand extends Command {
    public static final String COMMAND_WORD = "deadline";
    private final String taskName;
    private final String dueDate;

    /**
     * Initialises the name and the due date of the task from the input command.
     *
     * @param input The entire line of command entered by the user.
     * @throws DukeException If there is an error when getting the name or due date of the task.
     */
    public DeadlineCommand(String input) throws DukeException {
        int slashIndex = input.indexOf('/');
        this.taskName = getDeadlineTaskName(input, slashIndex);
        this.dueDate = getDueDate(input, slashIndex);
    }

    /**
     * Returns the name of the task from the input given by the user.
     *
     * @param input The entire line of command entered by the user.
     * @param slashIndex The index of the slash from the user's input.
     * @return Returns name of the task in String format.
     * @throws DukeException If the slash is not present in the input or if the task name is empty or filled with whitespaces.
     */
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
        //if taskName is just filled with whitespaces
        if (taskName.replace(" ", "").isEmpty()) {
            throw new DukeException("☹ OOPS!!! The name of a deadline task cannot be empty.");
        }
        return taskName;
    }

    /**
     * Adds a deadline task to the tasks list.
     * Prints a message to notify the user of the new deadline task added.
     */
    @Override
    public void execute() {
        taskList.addTask(new Deadline(taskName, dueDate));
        UI.printNewTaskMsg(taskList.getEntireList());
    }

    /**
     * Adds a deadline task to the tasks list without notifying the user.
     */
    @Override
    public void executeFromFile() {
        taskList.addTask(new Deadline(taskName, dueDate));
    }
}
