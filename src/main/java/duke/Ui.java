package duke;

import duke.task.Task;

import java.io.IOException;

public class Ui {
    private static TaskList tasks = new TaskList();
    private static Storage storage = new Storage("data/duke.txt");

    public void printHorizontalLine() {
        for (int i = 0; i < 11; i++) {
            System.out.print("-");
        }
        System.out.println("-");
    }

    public void printStartMessage() {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        printHorizontalLine();
    }

    public void printByeMessage() {
        printHorizontalLine();
        System.out.println("Bye. Hope to see you again soon!");
        printHorizontalLine();
    }

    public void addTaskToList(Task t, TaskList tasks) throws IOException {
        tasks.addTask(t);
        storage.writeToFile(tasks);

        printHorizontalLine();
        t.printTaskDisplay();
        System.out.println("Now you have " + tasks.getSize() + " tasks in the list");
        printHorizontalLine();



    }

    public void deleteTaskFromList (Task t, TaskList tasks) throws IOException {
        tasks.removeTask(t);
        storage.writeToFile(tasks);

        printHorizontalLine();
        System.out.println("Noted, I've removed this task:");
        String type = t.type;
        String icon = t.getStatusIcon();
        System.out.println("[" + type + "]" + " [" + icon + "] " + t.description);
        System.out.println("Now you have " + tasks.getSize() + " tasks in the list");
        printHorizontalLine();
    }
}
