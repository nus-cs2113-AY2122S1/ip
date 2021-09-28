package duke.command;

import duke.task.Task;

public class Ui {
    public static void sayHi() {
        System.out.println("Hello I'm Duke");
        System.out.println("What can I do for you?");
    }

    public static void markDone(int index, Task[] tasks) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + tasks[index].getStatusIcon() + " " + tasks[index].getDescription());
    }

    public static void markDoneFailed() {
        System.out.println("OOPS!!! The description of a mark-done cannot be empty.");
    }

    public static void deleteTask(int index, Task[] tasks, int taskNumber) {
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + tasks[index]);
        System.out.println("Now you have " + (taskNumber - 1) + " tasks in the list");
    }

    public static void deleteTaskFailed() {
        System.out.println("OOPS!!! The description of a deletion cannot be empty.");
    }

    public static void addTask(Task[] tasks, int taskNumber) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + tasks[taskNumber - 1]);
        System.out.println("Now you have " + taskNumber + " tasks in the list");
    }

    public static void addTodoFailed() {
        System.out.println("OOPS!!! The description of a todo cannot be empty.");
    }

    public static void addDeadlineFailed() {
        System.out.println("OOPS!!! The description of a deadline cannot be empty.");
    }

    public static void addEventFailed() {
        System.out.println("OOPS!!! The description of an event cannot be empty.");
    }

    public static void sayBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}
