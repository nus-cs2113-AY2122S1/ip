package Duke.Commands;

import Duke.DukeException;
import Duke.Task.Event;
import Duke.UI;

public class EventCommand extends Command {
    public static final String COMMAND_WORD = "event";
    private final String taskName;
    private final String dueDate;

    /**
     * Initialises the name and the due date of the task from the input command.
     *
     * @param input The entire line of command entered by the user.
     * @throws DukeException If there is an error when getting the name or due date of the task.
     */
    public EventCommand(String input) throws DukeException {
        int slashIndex = input.indexOf('/');
        this.taskName = getEventTaskName(input, slashIndex);
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
    private static String getEventTaskName(String input, int slashIndex) throws DukeException {
        final int EVENT_WORD_LENGTH = 6;
        int taskNameLastIndex = slashIndex - 1; //the last index of the task name
        //if the slash is not present in the input
        if (slashIndex == -1) {
            throw new DukeException("☹ OOPS!!! The description of an event task requires a task name" + System.lineSeparator()
                    + "\tfollowed by a front slash, and then a specific time after." + System.lineSeparator()
                    + "\ti.e. event team project meeting /on 2-10-2019 2-4pm");
        } else if (taskNameLastIndex <= EVENT_WORD_LENGTH) { //if the slash exists but the task name is empty
            throw new DukeException("☹ OOPS!!! The name of an event task cannot be empty.");
        }
        String taskName = input.substring(EVENT_WORD_LENGTH, taskNameLastIndex);
        //if taskName are just filled with whitespaces
        if (taskName.replace(" ", "").isEmpty()) {
            throw new DukeException("☹ OOPS!!! The name of an event task cannot be empty.");
        }
        return taskName;
    }

    /**
     * Adds an event task to the tasks list.
     * Prints a message to notify the user of the new event task added.
     */
    @Override
    public void execute() {
        taskList.addTask(new Event(taskName, dueDate));
        UI.printNewTaskMsg(taskList.getEntireList());
    }

    /**
     * Adds an event task to the tasks list without notifying the user.
     */
    @Override
    public void executeFromFile() {
        taskList.addTask(new Event(taskName, dueDate));
    }
}
