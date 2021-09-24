package duke;

import duke.tasks.Task;

import java.util.Scanner;

/**
 * This class serves as the user interface of the application. This class is responsible for
 * taking in input from the user, as well as displaying information to the command line based on the
 * user's command
 */
public class Ui {
    private final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private Scanner in;

    /**
     * Sets up the user interface so that it can read inputs from users via the command line
     */
    public Ui() {
        this.in = new Scanner(System.in);
    }

    /**
     * Displays the welcome message to users when the application first starts up
     */
    public void showWelcome() {
        System.out.println("Hello from\n" + LOGO);
        System.out.println("What can I do for you?");
    }

    /**
     * Display error message when the contents of the file is first loaded but is corrupted and cannot be read
     */
    public void showLoadingError() {
        System.out.println("There is some problem with the file...");
    }

    /**
     * Returns the input text that the user enters in the command line as a string to the application
     *
     * @return the input text as a string to be parsed
     */
    public String readCommand() {
        return in.nextLine();
    }

    /**
     * Display exception message stored in the exception object when exception is caught during the operation of the application.
     *
     * @param ex This is the exception object that is thrown if the parsing or execution of the command produces an error
     */
    public void showExceptionMessage(Exception ex) {
        System.out.println(ex.getMessage());
    }

    /**
     * Displays the goodbye message to users when the application terminates via 'bye' command
     */
    public void showGoodByeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Displays the task that is newly added to the task list
     *
     * @param task task that is newly added to the task list
     * @param tasks the task list that stores all the task
     */
    public void showAddMessage(Task task, TaskList tasks) {
        System.out.printf("Got it. I've added this task:\n" +
                "  %s\nNow you have %d task in the list\n"
                ,task, tasks.size());
    }

    /**
     * Displays the all the existing tasks in the task list
     *
     * @param tasks the task list that stores all the task
     */
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

    /**
     * Displays the task that is removed from the task list
     *
     * @param deletedTask task that has been removed from the task list
     * @param tasks the task list that stores all the task
     */
    public void showDeleteMessage(Task deletedTask, TaskList tasks) {
        System.out.printf("Noted. I've removed this task:\n" +
                "  %s\nNow you have %d task in the list.\n", deletedTask, tasks.size());
    }

    /**
     * Displays a message to the user that the task that they are trying to mark as done is already marked
     * and cannot be marked again
     */
    public void showAlreadyDoneMessage() {
        System.out.println("This task is already done!");
    }

    /**
     * Displays the task that has been marked as done
     *
     * @param task task that is marked as done
     */
    public void showDoneMessage(Task task) {
        System.out.printf("Nice! I've marked this task as done:\n" +
                "  %s\n", task);
    }

    /**
     * Displays a filtered task list based on a word specified by the user that they want
     * the description of the task to have
     *
     * @param filteredTasks task list containing only the task that contains the specified word
     *                      in the description
     * @param word the word in the task description that the user specify that the description must have
     */
    public void showFilteredTask(TaskList filteredTasks, String word) {
        if (filteredTasks.isEmpty()) {
            System.out.println("There are no task that match the input text: " + word);
        } else {
            showTaskList(filteredTasks);
        }
    }
}
