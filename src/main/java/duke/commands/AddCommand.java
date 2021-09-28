package duke.commands;

import duke.data.TaskList;
import duke.exception.StorageOperationException;
import duke.storage.Storage;
import duke.task.Task;
import duke.ui.Ui;

/**
 * Adds a task to the list.
 */
public class AddCommand extends Command{
    private final Task newTask;
    public static final String COMMAND_WORD_1 = "todo";
    public static final String COMMAND_WORD_2 = "deadline";
    public static final String COMMAND_WORD_3 = "event";

    public static final String MESSAGE_USAGE = "Duke provides three types of tasks: \n"
            + "1. ToDos\n" + COMMAND_WORD_1 + ": Adds a new ToDo to the task list. "
            + "ToDos without any date/time attached to it.\n"
            + "✪ Parameters: DESCRIPTION\n"
            + "⮞ Example: " + COMMAND_WORD_1
            + " borrow book\n"
            + System.lineSeparator()
            + "2. Deadlines\n" + COMMAND_WORD_2 + ": Adds a new Deadline to the task list. "
            + "Deadlines that need to be done before a specific date/time.\n"
            + "✪ Parameters: DESCRIPTION /by DATE\n"
            + "⮞ Example: " + COMMAND_WORD_2
            + " return book /by Sunday\n"
            + System.lineSeparator()
            + "3. Events\n" + COMMAND_WORD_3 + ": Adds a new Event to the task list. "
            + "Events that start at a specific time and ends at a specific time.\n"
            + "✪ Parameters: DESCRIPTION /at TIME\n"
            + "⮞ Example: " + COMMAND_WORD_3
            + " project meeting /at Mon 2-4pm";

    /**
     * Convenience constructor using processed Task data
     *
     * @param task A new task
     */
    public AddCommand(Task task){
        this.newTask = task;
    }

    /**
     * Adds a task to the list
     *
     * @param tasks Recorded list of tasks
     * @param ui Used ui
     * @param storage Used storage
     * @throws StorageOperationException
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws StorageOperationException {
        tasks.addTask(newTask);
        System.out.println("Got it. I've added this task: ");
        System.out.println(newTask);
        System.out.println("Now you have " + tasks.getSize() + " task(s) in the list.");
        storage.save(tasks);
    }


}
