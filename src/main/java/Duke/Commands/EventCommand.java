package Duke.Commands;

import Duke.DukeException;
import Duke.Task.Deadline;
import Duke.Task.Event;
import Duke.Task.Task;
import Duke.UI;

import java.util.ArrayList;

public class EventCommand extends Command {
    public static final String COMMAND_WORD = "event";
    private final String taskName;
    private final String dueDate;

    public EventCommand(String input) throws DukeException {
        int slashIndex = input.indexOf('/');
        this.taskName = getEventTaskName(input, slashIndex);
        this.dueDate = getDueDate(input, slashIndex);
    }

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

    @Override
    public void execute(ArrayList<Task> tasksList) {
        tasksList.add(new Event(taskName, dueDate));
        UI.printNewTaskMsg(tasksList);
    }

    @Override
    public void executeFromFile(ArrayList<Task> tasksList) {
        tasksList.add(new Event(taskName, dueDate));
    }
}
