package duke.ui;

import duke.task.Task;

import java.util.ArrayList;

public class Ui {

    public static final String DIVIDER = "========================================================================";

    /**
     * Prints the greeting message of Duke
     */
    public void printGreetingMessage() {
        System.out.println(DIVIDER);
        System.out.println("Hello! I am Duke, here to manage your tasks!");
        System.out.println(DIVIDER);
    }

    /**
     * Prints the farewell message of Duke
     */
    public void printGoodbyeMessage() {
        System.out.println(DIVIDER);
        System.out.println("Bye! Hope to see you again soon!");
        System.out.println(DIVIDER);
    }

    /**
     * Prints the error message for an invalid command
     */
    public void printInvalidCommand() {
        System.out.println(DIVIDER);
        System.out.println("Command not recognized!");
        System.out.println("try the following: \"list\", \"done\", \"todo\", \"deadline\", \"event\", \"bye\"");
        System.out.println("\"delete\", \"find\"");
        System.out.println(DIVIDER);
    }

    /**
     * Prints the prompt for user to give a numerical number.
     */
    public void printInvalidNumber() {
        System.out.println(DIVIDER);
        System.out.println("Please give a number!");
        System.out.println(DIVIDER);
    }

    /**
     * Prints out the instruction for the correct usage of the command.
     *
     * @param commandUsageMethod a string representing the command usage format
     */
    public void printCommandGuide(String commandUsageMethod) {
        System.out.println(DIVIDER);
        System.out.println("Instructions for the command: " + commandUsageMethod);
        System.out.println(DIVIDER);
    }

    /**
     * Prints out a list of all tasks in the task ArrayList
     *
     * @param taskList the task ArrayList with all the tasks to be printed
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
     * @param taskList the array list the task is in
     * @param taskIndex the array index of the task that was marked as done
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
     * @param removedTask the Task that was removed
     * @param taskIndex the array index of the task that was deleted
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

    public void printSearchList(ArrayList<Task> taskList, String keyword) {
        int matchCount = 0;
        System.out.println(DIVIDER);
        System.out.println("Here are the matching tasks in your list:");
        for (Task task : taskList) {
            String[] descriptionWords = task.getDescription().split(" ");
            for (String descriptionWord : descriptionWords) {
                if (descriptionWord.equals(keyword)) {
                    matchCount++;
                    System.out.println((matchCount) + "." + task);
                    //go to the next task when once a match is found
                    break;
                }
            }
        }
        if (matchCount == 0) {
            System.out.println("No matching tasks found!");
        }
        System.out.println(DIVIDER);
    }

    /**
     * Prints a message to remind the user to only use one keyword to search
     */
    public void printOnlyUseOneKeyword() {
        System.out.println(DIVIDER);
        System.out.println("I can only search for one keyword at a time!");
        System.out.println(DIVIDER);
    }
}
