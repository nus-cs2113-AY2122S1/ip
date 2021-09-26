package commands;

import task.Deadline;
import task.Event;
import task.Task;
import tasklist.TaskList;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ListCommand extends Command{

    private final  LocalDate dateTime;
    public static final String LIST_IS_EMPTY = "You're a free man :)";
    private static final String LIST_IS_NOT_EMPTY = "Here are the things you need to do :";
    private static final String LIST_OF_SPECIFIC_DATE = "Here are the things you need to do on that day :";
    public static final String LIST_COMMAND = "list";
    public static final int NO_TASKS = 0;

    public ListCommand () {
        super();
        this.dateTime = null;
    }

    public ListCommand(LocalDate dateTime) {
        super();
        this.dateTime = dateTime;
    }

    public CommandResult execute(){
        if(Task.getTotalTasks() == NO_TASKS) {
            return new CommandResult(LIST_IS_EMPTY,PrintOptions.DEFAULT);

        } else if (dateTime != null){
            TaskList tasksOfSpecificDate = getTasksOfSpecificDate();
            return new CommandResult(LIST_OF_SPECIFIC_DATE,tasksOfSpecificDate,PrintOptions.LIST_OF_SPECIFIC_DATE);
        }
        return new CommandResult(LIST_IS_NOT_EMPTY,tasks,PrintOptions.LIST);
    }

    private TaskList getTasksOfSpecificDate() {
        TaskList tasksOfSpecificDate = new TaskList();
        int totalTasks = 0;
        for (int i = 0; i <Task.getTotalTasks(); i++) {
            Task temp = tasks.getTask(i);
            if (taskWithSameDate(temp) != null) {
                tasksOfSpecificDate.addTaskForListOfSpecificDate(taskWithSameDate(temp));
                totalTasks++;
            }
        }
        tasksOfSpecificDate.setNumberOfTaskForTheSameDate(totalTasks);
        return tasksOfSpecificDate;
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
