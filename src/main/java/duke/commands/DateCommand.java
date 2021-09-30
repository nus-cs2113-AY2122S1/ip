package duke.commands;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Filters {@link Event} and {@link Deadline} tasks based on date.
 */
public class DateCommand extends Command {
    /** Unique word associated with the command. */
    public static final String COMMAND_WORD = "date";

    private static final String MESSAGE_MATCHING_TASK_LIST = "Here are the tasks in your list that fall on %1$s:\n"
            + "%2$s";

    private final LocalDate dateToFilterBy;

    /**
     * Instantiates command and stores date.
     *
     * @param dateToFilterBy Date to filter tasks by.
     */
    public DateCommand(LocalDate dateToFilterBy) {
        this.dateToFilterBy = dateToFilterBy;
    }

    @Override
    public String execute(TaskList tasks) {
        final ArrayList<String> formattedTasks = new ArrayList<>();
        for (int i = 0; i < tasks.getSize(); i++) {
            final Task task = tasks.getTask(i);
            if (hasSameDate(task)) {
                formattedTasks.add(Ui.formatTaskForTaskList(i + 1, task));
            }
        }
        String taskListOutput = String.join("\n", formattedTasks);
        return String.format(MESSAGE_MATCHING_TASK_LIST, dateToFilterBy.format(Ui.DATE_OUTPUT_FORMATTER), taskListOutput);
    }

    /**
     * Checks whether the given {@code task} has the same date as the date to filter by ({@link #dateToFilterBy}).
     *
     * @param task The task
     * @return True if the task is an {@link Event} or {@link Deadline} task and has the same date as {@link
     *         #dateToFilterBy}; false otherwise.
     */
    private Boolean hasSameDate(Task task) {
        if (task instanceof Deadline) {
            final LocalDateTime deadline = ((Deadline) task).getBy();
            return deadline.toLocalDate().equals(dateToFilterBy);
        } else if (task instanceof Event) {
            final LocalDateTime eventDateTime = ((Event) task).getAt();
            return eventDateTime.toLocalDate().equals(dateToFilterBy);
        }
        return false;
    }
}
