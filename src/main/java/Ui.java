import task.Task;

import java.util.ArrayList;

public class Ui {

    public static void printDivider() {
        System.out.println("____________________________________________________________");
    }

    public static void printNumberFormatExceptionMessage() {
        System.out.println("Please key in a number");
        printDivider();
    }

    public void printGreetings() {
        System.out.println("Welcome to Duke! What can I do for you?");
        printDivider();
    }

    public void printGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
        printDivider();
    }

    public static void echoLastTask() {
        int index = TaskManager.tasks.size() - 1;
        printNewlyAddedTask(index);
        Ui.printTaskCount();
        Ui.printDivider();
    }

    public static void printTaskCount() {
        int count = TaskManager.tasks.size();
        if (count == 0) {
            System.out.print("You have not added any tasks");
        } else if (count == 1) { //grammar
            System.out.print("Now you have 1 task in the list" + System.lineSeparator());
        } else {
            System.out.printf("Now you have %d tasks in the list" + System.lineSeparator(), count);
        }
    }

    public static void printDeleteTaskMessage(int index) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(TaskManager.tasks.get(index));
        System.out.println("Now you have " + (TaskManager.tasks.size() - 1) + " tasks in the list");
        printDivider();
    }

    public static void printNewlyAddedTask(int index) {
        System.out.println("Got it. I've added this task:");
        System.out.println(TaskManager.tasks.get(index));
    }

    public static void printInvalidCommandMessage() {
        System.out.println("☹ OOPS!!! I do not understand what that means!");
        printDivider();
    }

    public static void printEmptyTimeExceptionMEssage() {
        System.out.println("☹ OOPS!!! You forgot to add the date/time.");
        System.out.println("Format: {type} {description} /by {datetime} ");
        System.out.println("Example: deadline coding project /by 2021-12-25");
        printDivider();
    }

    public static void printDukeExceptionMessage(String command) {
        if (command.equalsIgnoreCase("done")) {
            System.out.println("☹ OOPS!!! You've forgotten to write the task number");
        } else if (command.equalsIgnoreCase("delete")) {
            System.out.println("☹ OOPS!!! You've forgotten to write the task number");
        } else if (command.equalsIgnoreCase("find")) {
            System.out.println("☹ OOPS!!! What are you finding?");
        } else {
            System.out.printf("☹ OOPS!!! The description of a %s cannot be empty" + System.lineSeparator(), command);
        }
        printDivider();
    }

    public static void printOutOfBoundsMessage() {
        int taskCount = TaskManager.tasks.size();
        if (taskCount == 0) {
            System.out.println("You have not added any tasks yet");
        } else if (taskCount == 1) {
            System.out.println("You only have 1 task");
        } else {
            System.out.println("Please pick a number from 1 to " + taskCount);
        }
    }

    public static void printMarkAsDoneMessage(int index) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(TaskManager.tasks.get(index));
    }

    public static void printNoDataFoundMessage() {
        System.out.println("New user, no local data found");
    }

    public static void printDataSavedMessage() {
        System.out.println("Data is saved to hard drive.");
    }

    public static void printFileCreatedMessage() {
        System.out.println("Local file has been created.");
    }

    public static void printMatchingString(ArrayList<Task> tasks, String userInput) {
        System.out.printf("There are %d tasks containing \"%s\"" + System.lineSeparator(), tasks.size(), userInput.split(" ",2)[1]);
        for (Task t : tasks) {
            System.out.println(t);
        }
        printDivider();
    }

    public static void printTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < TaskManager.tasks.size(); i++) {
            System.out.printf("%d.%s" + System.lineSeparator(), i + 1, TaskManager.tasks.get(i));
        }
        printDivider();
    }
}
