package Duke;

import Duke.Task.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class UI {
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

    public static void printByeMessage() {
        System.out.println("\tGoodbye! Hope to see you again soon!");
        printBorder();
    }

    public static void printBorder() {
        System.out.println("\t----------------------------------------------------------------------");
    }

    public static String getInput() {
        String input;
        Scanner scanner = new Scanner(System.in);
        input = scanner.nextLine();
        printBorder();
        return input;
    }

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

    public static void printNewTaskMsg(ArrayList<Task> tasksArrayList) {
        int taskCount = tasksArrayList.size();
        int taskIndex = taskCount - 1;
        System.out.println("\tAlright! I've just added this task:");
        System.out.println("\t" + tasksArrayList.get(taskIndex).toString());
        System.out.println("\tYou now have " + taskCount + " tasks on your task list.");
        printBorder();
    }

}
