package duke;

import duke.task.Task;

import java.io.IOException;

/**
 * A class that reads user input and displays messages for the user
 */
public class Ui {
    private static TaskList tasks = new TaskList();
    private static Storage storage = new Storage("duke.txt");

    /**
     * Prints a horizontal line separator between commands
     */
    public void printHorizontalLine() {
        for (int i = 0; i < 11; i++) {
            System.out.print("-");
        }
        System.out.println("-");
    }

    /**
     * Prints duke's greeting message
     */
    public void printStartMessage() {
        System.out.println("Hello from");
        System.out.println(" ____        _        \n" +
                "|  _ \\ _   _| | _____ \n" +
                "| | | | | | | |/ / _ \\\n" +
                "| |_| | |_| |   <  __/\n" +
                "|____/ \\__,_|_|\\_\\___");

        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        printHorizontalLine();
    }

    /**
     * Prints duke's goodbye message when application is exited
     */
    public void printByeMessage() {
        printHorizontalLine();
        System.out.println("Bye. Hope to see you again soon!");
        printHorizontalLine();
    }

    /**
     * Adds task to task list
     *
     * @param t Task to be added
     * @param tasks Task list that task is to be added to
     * @throws IOException If the external storage file cannot be found when attempting to write
     */
    public void addTaskToList(Task t, TaskList tasks) throws IOException {
        tasks.addTask(t);
        storage.writeToFile(tasks);

        printHorizontalLine();
        t.printTaskDisplay();
        System.out.println("Now you have " + tasks.getSize() + " tasks in the list");
        printHorizontalLine();
    }

    /**
     * Deletes task from task list
     *
     * @param t Task to be deleted
     * @param tasks Task list to delete task from
     * @throws IOException If the external storage file cannot be found when attempting to write
     */
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
