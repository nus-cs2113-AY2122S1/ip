package Duke;

import Duke.Task.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class UI {

    /**
     * Prints the opening message.
     */
    public static void printHeaderMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);

        System.out.println("\tHey, how are you?");
        System.out.println("\tWhat can I do for you today?");
        printBorder();
    }

    /**
     * Prints the program exit message.
     */
    public static void printByeMessage() {
        System.out.println("\tGoodbye! Hope to see you again soon!");
        printBorder();
    }

    /**
     * Prints the border surrounding each output message.
     */
    public static void printBorder() {
        System.out.println("\t----------------------------------------------------------------------");
    }

    /**
     * Gets the input entered by the user and returns it in a string.
     *
     * @return Returns the input entered by the user in a string format.
     */
    public static String getInput() {
        String input;
        Scanner scanner = new Scanner(System.in);
        input = scanner.nextLine();
        printBorder();
        return input;
    }

    /**
     * Prints the entire tasks list out.
     *
     * @param taskList Current task list that is in use.
     */
    public static void printListMessage(TaskList taskList) {
        if (taskList.getSize() == 0) {
            System.out.println("\tThe list is empty!");
        } else {
            System.out.println("\tHere's the list of your tasks: ");
            for (int j = 0; j < taskList.getSize(); j++) {
                int itemNumber = j + 1;
                System.out.println("\t" + itemNumber + "." + taskList.getTask(j).toString());
            }
        }
        printBorder();
    }

    /**
     * Prints a message to notify the user of the new task being added.
     *
     * @param tasksArrayList Arraylist containing all the task.
     */
    public static void printNewTaskMsg(ArrayList<Task> tasksArrayList) {
        int taskCount = tasksArrayList.size();
        int taskIndex = taskCount - 1;
        System.out.println("\tAlright! I've just added this task:");
        System.out.println("\t" + tasksArrayList.get(taskIndex).toString());
        System.out.println("\tYou now have " + taskCount + " tasks on your task list.");
        printBorder();
    }

    /**
     * Prints the list of tasks that satisfied the query.
     *
     * @param tasksFoundArrayList Arraylist of tasks that satisfied the query.
     */
    public static void printTasksFound(ArrayList<Task> tasksFoundArrayList) {
        if (tasksFoundArrayList.size() == 0) {
            System.out.println("\tThere are no matching tasks in your list!");
        } else {
            System.out.println("\tThe following are the tasks found: ");
            int itemNumber = 1;
            for (Task task : tasksFoundArrayList) {
                System.out.println("\t" + itemNumber + "." + task.toString());
                itemNumber++;
            }
        }
        printBorder();
    }

    /**
     * Prints a message to notify the user after a task is marked as done.
     *
     * @param taskList Current task list that is in use.
     * @param taskIndex Index of task to be marked as done.
     */
    public static void printMarkedAsDoneMessage(TaskList taskList, int taskIndex){
        System.out.println("\tGood job! I've marked this task as done: ");
        System.out.println("\t" + taskList.getTask(taskIndex));
    }

}
