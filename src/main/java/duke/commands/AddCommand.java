package duke.commands;

import duke.data.Duke;
import duke.data.TaskList;
import duke.exception.DukeException;
import duke.exception.StorageOperationException;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Events;
import duke.task.Task;
import duke.task.ToDos;
import duke.ui.Ui;

public class AddCommand extends Command{
    private final Task newTask;
    public static final String COMMAND_WORD_1 = "todo";
    public static final String COMMAND_WORD_2 = "deadline";
    public static final String COMMAND_WORD_3 = "event";

    public static final String MESSAGE_USAGE = "Provides three types of tasks: "
            + "1. ToDos\n" + COMMAND_WORD_1 + ": Adds a new ToDo to the task list. "
            + "ToDos without any date/time attached to it.\n"
            + "Parameters: DESCRIPTION\n"
            + "⮞ Example: " + COMMAND_WORD_1
            + " borrow book\n"
            + "2. Deadlines\n" + COMMAND_WORD_2 + ": Adds a new Deadline to the task list. "
            + "Deadlines that need to be done before a specific date/time.\n"
            + "Parameters: DESCRIPTION /by DATE\n"
            + "⮞ Example: " + COMMAND_WORD_2
            + " return book /by Sunday\n"
            + "3. Events\n" + COMMAND_WORD_3 + ": Adds a new Event to the task list. "
            + "Events that start at a specific time and ends at a specific time.\n"
            + "Parameters: DESCRIPTION /at TIME\n"
            + "⮞ Example: " + COMMAND_WORD_3
            + " project meeting /at Mon 2-4pm\n";

    public AddCommand(Task task){
        this.newTask = task;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws StorageOperationException {
        tasks.addTask(newTask);
        System.out.println("Got it. I've added this task: ");
        System.out.println(newTask);
        System.out.println("Now you have " + tasks.getSize() + " task(s) in the list.");
        storage.save(tasks);
    }


}
