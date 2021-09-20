package duke.ui;

import duke.task.Task;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.TaskTimeManager;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Deals with interactions with the user by printing messages in the terminal.
 */
public class Ui {

    public static final String DIVIDER = "========================================================================";
    private static final TaskTimeManager taskTimeManager = new TaskTimeManager();

    /**
     * Prints the greeting message of Duke.
     */
    public void printGreetingMessage() {
        System.out.println(DIVIDER);
        System.out.println("Hello! I am Duke, here to manage your tasks!");
        System.out.println(DIVIDER);
    }

    /**
     * Prints the goodbye message of Duke.
     */
    public void printGoodbyeMessage() {
        System.out.println(DIVIDER);
        System.out.println("Bye! Hope to see you again soon!");
        System.out.println(DIVIDER);
    }

    /**
     * Prints the error message for an invalid command and lists out possible
     * commands for the user.
     */
    public void printInvalidCommand() {
        System.out.println(DIVIDER);
        System.out.println("Command not recognized!");
        System.out.println("try the following: \"list\", \"done\", \"todo\", \"deadline\", \"event\", \"bye\"");
        System.out.println("\"delete\", \"upcoming\"");
        System.out.println(DIVIDER);
    }

    /**
     * Prints the prompt for user to give a numerical number as an argument.
     */
    public void printInvalidNumber() {
        System.out.println(DIVIDER);
        System.out.println("Please give a number!");
        System.out.println(DIVIDER);
    }

    /**
     * Prints out the instruction for the correct format for usage of the command.
     *
     * @param commandUsageMethod a string representing the command usage format
     */
    public void printCommandGuide(String commandUsageMethod) {
        System.out.println(DIVIDER);
        System.out.println("Instructions for the command: " + commandUsageMethod);
        System.out.println(DIVIDER);
    }

    /**
     * Prints out a list of all tasks in the task list
     *
     * @param taskList the task list containing all the tasks to be printed
     */
    public void printTaskList(ArrayList<Task> taskList) {
        System.out.println(DIVIDER);
        if (Task.getTotalTasks() == 0) {
            System.out.println("There are no tasks!");
            System.out.println(DIVIDER);
            return;
        }
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < Task.getTotalTasks(); i++) {
            System.out.println((i + 1) + "." + taskList.get(i));
        }
        System.out.println(DIVIDER);
    }

    /**
     * Prints out the success or fail message for the marking of a task as done
     *
     * @param taskList the full task list
     * @param taskIndex the array index of the task that was marked as done in the task list
     * @param isSuccessful the result of the task marking
     */
    public void printMarkTaskAsDone(ArrayList<Task> taskList, int taskIndex, boolean isSuccessful) {
        System.out.println(DIVIDER);
        if (isSuccessful) {
            System.out.println("Nice! I've marked task number " + (taskIndex + 1) + " as done:");
            System.out.println("  " + taskList.get(taskIndex));
        } else {
            System.out.println("The given task number does not exist!");
            System.out.println("You have " + Task.getTotalTasks() + " tasks in total.");
        }
        System.out.println(DIVIDER);
    }

    /**
     * Prints out the success or fail message for the deleting of a task
     *
     * @param removedTask the task that was deleted
     * @param taskIndex the array index of the task that was deleted in the task list
     * @param isSuccessful the result of the task deletion
     */
    public void printDeleteTask(Task removedTask, int taskIndex, boolean isSuccessful) {
        System.out.println(DIVIDER);
        if (isSuccessful) {
            System.out.println("Noted. I've removed task number " + (taskIndex + 1) + ":");
            System.out.println("  " + removedTask);
            System.out.println("Now you have " + Task.getTotalTasks() + " tasks in the list.");
        } else {
            System.out.println("The given task number does not exist!");
            System.out.println("You have " + Task.getTotalTasks() + " tasks in total.");
        }
        System.out.println(DIVIDER);
    }

    /**
     * Prints out the prompt for the user to specify a description and/or date for the
     * task.
     *
     * @param taskType a string describing the task type
     */
    public void printMissingParameter(String taskType) {
        System.out.println(DIVIDER);
        System.out.println("Please specify the " + taskType + " description and/or date!");
        System.out.println(DIVIDER);
    }

    /**
     * Prints the success message for the most recently added task
     */
    public void printTaskAddedMessage(ArrayList<Task> taskList) {
        System.out.println(DIVIDER);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + taskList.get(Task.getTotalTasks() - 1));
        System.out.println("Now you have " + Task.getTotalTasks() + " tasks in the list.");
        System.out.println(DIVIDER);
    }

    /**
     * Prints the message to prompt user to input the date and time in the required
     * format.
     */
    public void printWrongDateTimeFormat() {
        System.out.println(DIVIDER);
        System.out.println("Wrong Date-Time format given!");
        System.out.println("Accepted Format: YYYY-MM-DD HH:MM");
        System.out.println("Example: 2021-09-18 16:00");
        System.out.println(DIVIDER);
    }

    /**
     * Prints out the list of upcoming deadlines within the specified number
     * of days (1day=24hr)
     *
     * @param taskList the full task list
     * @param days the number of days to print the deadlines up to
     */
    public void printUpcomingDeadlines(ArrayList<Task> taskList, int days) {
        //gets the Date-Time to print deadlines up to
        LocalDateTime due = taskTimeManager.addDaysToDateTime(taskTimeManager.getCurrentTime(), days);
        System.out.println(DIVIDER);
        System.out.println("Here are the tasks due within the next " + days + " days:");
        int printCount = 0;
        for (int i = 0; i < Task.getTotalTasks(); i++) {
            if (taskList.get(i) instanceof Deadline) {
                Deadline deadlineTask = (Deadline)taskList.get(i);
                if (taskTimeManager.isEarlierThan(deadlineTask.getBy(), due)) {
                    System.out.println((printCount + 1) + "." + taskList.get(i));
                    printCount++;
                }
            }
        }
        if (printCount == 0) {
            System.out.println("You have no deadlines within the next " + days + " days!");
        }
        System.out.println(DIVIDER);
    }

    /**
     * Prints out the list of upcoming events within the specified number of
     * days (1day=24hr)
     *
     * @param taskList the full task list
     * @param days the number of days to print the events up to
     */
    public void printUpcomingEvents(ArrayList<Task> taskList, int days) {
        //gets the Date-Time to print events up to
        LocalDateTime due = taskTimeManager.addDaysToDateTime(taskTimeManager.getCurrentTime(), days);
        System.out.println(DIVIDER);
        System.out.println("Here are the upcoming events within the next " + days + " days:");
        int printCount = 0;
        for (int i = 0; i < Task.getTotalTasks(); i++) {
            if (taskList.get(i) instanceof Event) {
                Event eventTask = (Event)taskList.get(i);
                if (taskTimeManager.isEarlierThan(eventTask.getAt(), due)) {
                    System.out.println((printCount + 1) + "." + taskList.get(i));
                    printCount++;
                }
            }
        }
        if (printCount == 0) {
            System.out.println("You have no events within the next " + days + " days!");
        }
        System.out.println(DIVIDER);
    }

}
