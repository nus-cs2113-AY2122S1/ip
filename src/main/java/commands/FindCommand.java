package commands;

import task.Deadline;
import task.Event;
import task.Task;
import tasklist.TaskList;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * A Command class that contains methods for finding tasks with specific description or date.
 */
public class FindCommand extends Command{

    private final String input;
    private final LocalDate dateTime;
    private static final String LIST_CONTAINING_STRING_MESSAGE = "Here are the matching tasks in your list : ";
    private static final String LIST_OF_SPECIFIC_DATE = "Here are the things you need to do on that day :";
    private static final String NO_SIMILAR_WORDS = "There are no matching tasks :(";
    public static final String FIND_COMMAND = "find";

    public FindCommand(String input) {
        this.input = input;
        this.dateTime = null;
    }
    public FindCommand(LocalDate dateTime) {
        this.input = null;
        this.dateTime = dateTime;
    }

    /**
     * Searches through the ArrayList for tasks which contains the descriptions
     * given or have the same date.
     *
     * @return A CommandResult that tells the Ui to print the status of the execution
     * and the list of tasks with similar descriptions / dates (if successful).
     */
    public CommandResult execute() {
        int totalTasks;
        String message;
        TaskList taskContainingInput = new TaskList();
        if(input == null) {
            totalTasks = getTasksOfSpecificDate(taskContainingInput);
            message = LIST_OF_SPECIFIC_DATE;
        } else {
            totalTasks = getTasksOfSpecificString(taskContainingInput);
            message = LIST_CONTAINING_STRING_MESSAGE;
        }
        taskContainingInput.setNumberOfTask(totalTasks);
        if(totalTasks == 0) {
            return new CommandResult(NO_SIMILAR_WORDS,PrintOptions.DEFAULT);
        }
        return new CommandResult(message,
                taskContainingInput,PrintOptions.LIST_WITH_SPECIFIC_CONDITIONS);
    }

    /**
     * Iterates through the ArrayList to get tasks with specific descriptions
     *
     * @param taskContainingString The new TaskList created for adding tasks
     *                             with specific descriptions
     * @return The total number of tasks in the new TaskList
     */
    private int getTasksOfSpecificString(TaskList taskContainingString) {
        int totalTasks = 0;
        for (int i = 0; i < Task.getTotalTasks(); i++) {
            if(tasks.getTask(i).getDescription().contains(input)){
                taskContainingString.addTaskForFindCommand(tasks.getTask(i));
                totalTasks++;
            }
        }
        return totalTasks;
    }

    /**
     * Iterates through the ArrayList to get tasks with a specific date
     *
     * @param tasksOfSpecificDate The new TaskList created for adding tasks
     *                             with a specific date
     * @return The total number of tasks in the new TaskList
     */
    private int getTasksOfSpecificDate(TaskList tasksOfSpecificDate) {
        int totalTasks = 0;
        for (int i = 0; i <Task.getTotalTasks(); i++) {
            Task temp = tasks.getTask(i);
            if (taskWithSameDate(temp) != null) {
                tasksOfSpecificDate.addTaskForFindCommand(taskWithSameDate(temp));
                totalTasks++;
            }
        }
        return totalTasks;
    }

    private Task taskWithSameDate(Task temp) {
        if(temp instanceof Event && sameDate(((Event) temp).getFromDateAndTime())) {
            return temp;
        } else if(temp instanceof Deadline && sameDate(((Deadline) temp).getDateAndTime())){
            return temp;
        } else {
            return null;
        }
    }

    private Boolean sameDate (LocalDateTime date) {
        Boolean isSameYear = date.getYear() == dateTime.getYear();
        Boolean isSameDayOfYear = date.getDayOfYear() == dateTime.getDayOfYear();
        return (isSameYear && isSameDayOfYear);
    }
}
