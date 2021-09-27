package commands;

import task.Deadline;
import task.Event;
import task.Task;
import tasklist.TaskList;

import java.time.LocalDate;
import java.time.LocalDateTime;

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

    public CommandResult execute() {
        int totalTasks = 0;
        String message;
        TaskList taskContainingInput = new TaskList();
        if(input == null) {
            totalTasks = getTasksOfSpecificDate(totalTasks, taskContainingInput);
            message = LIST_OF_SPECIFIC_DATE;
        } else {
            totalTasks = getTasksOfSpecificString(totalTasks, taskContainingInput);
            message = LIST_CONTAINING_STRING_MESSAGE;
        }
        taskContainingInput.setNumberOfTask(totalTasks);
        if(totalTasks == 0) {
            return new CommandResult(NO_SIMILAR_WORDS,PrintOptions.DEFAULT);
        }
        return new CommandResult(message,
                taskContainingInput,PrintOptions.LIST_WITH_SPECIFIC_CONDITIONS);
    }

    private int getTasksOfSpecificString(int totalTasks, TaskList taskContainingString) {
        for (int i = 0; i < Task.getTotalTasks(); i++) {
            if(tasks.getTask(i).getDescription().contains(input)){
                taskContainingString.addTaskForSpecificCases(tasks.getTask(i));
                totalTasks++;
            }
        }
        return totalTasks;
    }

    private int getTasksOfSpecificDate(int totalTasks,TaskList tasksOfSpecificDate) {

        for (int i = 0; i <Task.getTotalTasks(); i++) {
            Task temp = tasks.getTask(i);
            if (taskWithSameDate(temp) != null) {
                tasksOfSpecificDate.addTaskForSpecificCases(taskWithSameDate(temp));
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
        return (date.getYear() == dateTime.getYear() && date.getDayOfYear() == dateTime.getDayOfYear());
    }
}
