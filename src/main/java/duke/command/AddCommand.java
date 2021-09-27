package duke.command;

import duke.task.*;
import duke.exception.EmptyTaskException;
import duke.ui.Ui;

public class AddCommand extends Command {

    public static final int FIND_TASK_TODO = 5;
    public static final int FIND_TASK_DEADLINE = 9;
    public static final int FIND_TASK_EVENT = 6;
    public static final int FIND_TIME = 4;
    private static String order;
    public AddCommand(String order) {
        this.order = order;
    }

    /**
     * Add a task into the list
     *
     * @param list The list of all tasks
     * @param doneList The list of all tasks which have been finished
     * @param ui The ui that is used
     * @throws EmptyTaskException If the description of task is empty,
     *                            exception occurs
     */
    @Override
    public void executeCommand(TaskList list, TaskDoneList doneList, Ui ui) throws EmptyTaskException {
        try {
            if (order.contains("todo")) {
                String task = order.substring(order.indexOf("todo") + FIND_TASK_TODO);
                list.addTask(new Todo(task));
            } else if (order.contains("event")) {
                String task = order.substring(order.indexOf("event")
                    + FIND_TASK_EVENT, order.indexOf("/") - 1);
                String at = order.substring(order.indexOf("/at") + FIND_TIME);
                list.addTask(new Event(task, at));
            } else if (order.contains("deadline")) {
                String task = order.substring(order.indexOf("deadline")
                    + FIND_TASK_DEADLINE, order.indexOf("/") - 1);
                String by = order.substring(order.indexOf("/by") + FIND_TIME);
                list.addTask(new Deadline(task, by));
            }
            ui.printAddCommand(list, ui);
        } catch (StringIndexOutOfBoundsException e) {
            throw new EmptyTaskException();
        }
    }
}
