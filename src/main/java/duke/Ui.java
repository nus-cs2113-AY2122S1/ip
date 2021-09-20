package duke;

import java.util.Scanner;

public class Ui {
    private final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private Scanner in;

    public Ui() {
        this.in = new Scanner(System.in);
    }
    public void showWelcome() {
        System.out.println("Hello from\n" + LOGO);
        System.out.println("What can I do for you?");
    }

    public void showLoadingError() {
        System.out.println("There is some problem with the file...");
    }

    public String readCommand() {
        return in.nextLine();
    }

    public void showExceptionMessage(Exception ex) {
        System.out.println(ex.getMessage());
    }

    public void showGoodByeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void showAddMessage(Task task, TaskList tasks) {
        System.out.printf("Got it. I've added this task:\n" +
                "  %s\nNow you have %d task in the list\n"
                ,task, tasks.size());
    }

    public void showTaskList(TaskList tasks) {
        if (tasks.isEmpty()) {
            System.out.println("Take a chill pill! Your todo list is empty");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.printf("%d. %s\n", i + 1, tasks.getTask(i));
            }
        }
    }

    public void showDeleteMessage(Task deletedTask, TaskList tasks) {
        System.out.printf("Noted. I've removed this task:\n" +
                "  %s\nNow you have %d task in the list.\n", deletedTask, tasks.size());
    }

    public void showAlreadyDoneMessage() {
        System.out.println("This task is already done!");
    }

    public void showDoneMessage(Task task) {
        System.out.printf("Nice! I've marked this task as done:\n" +
                "  %s\n", task);
    }
}
