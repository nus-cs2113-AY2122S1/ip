package duke;

import duke.exception.EmptyDescriptionException;
import duke.exception.MissingParameterException;
import duke.exception.TaskNotFoundException;
import duke.exception.WrongCommandException;
import duke.task.Task;

import java.util.ArrayList;

public class DukeUI {
    private static final int DEFAULT_LINE_LENGTH = 60;
    private static final String LINEBREAK = System.lineSeparator();
    private static final String LOGO = " ____        _" + LINEBREAK
            + "|  _ \\ _   _| | _____" + LINEBREAK
            + "| | | | | | | |/ / _ \\" + LINEBREAK
            + "| |_| | |_| |   <  __/" + LINEBREAK
            + "|____/ \\__,_|_|\\_\\___|" + LINEBREAK;

    /**
     * Greet the user when the program is initialized
     */
    public static void greet() {
        drawHorizontalLine();
        System.out.println("Hello! I'm Duke, your personal assistant.");
        System.out.println("How can I help you?");
        drawHorizontalLine();
    }

    /**
     * Say goodbye before terminate the program
     */
    public static void sayGoodbye() {
        drawHorizontalLine();
        System.out.println("Bye. Hope to see you again soon!");
        drawHorizontalLine();
    }

    /**
     * Draw a horizontal line to separate text command
     */
    public static void drawHorizontalLine() {
        for (int i = 0; i < DEFAULT_LINE_LENGTH; i++) {
            System.out.print("\u2500");
        }
        System.out.println();
    }

    public static void printTaskList(ArrayList<Task> tasks) {
        drawHorizontalLine();
        if (tasks.size() == 0) {
            System.out.println("No task added yet!");
        } else {
            System.out.println("Here is your list at the moment:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.printf("%d. %s" + LINEBREAK, i + 1, tasks.get(i).toString());
            }
        }
        drawHorizontalLine();
    }

    /**
     * @param task the newly added task
     * @param taskListSize the size of the list after the addition of the new task
     */
    public static void printCompleteAddTask(Task task, int taskListSize) {
        drawHorizontalLine();
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.printf("Now you have %d tasks in the list" + LINEBREAK, taskListSize);
        drawHorizontalLine();
    }

    /**
     * @param task the completed task
     */
    public static void printMarkTaskDone(Task task) {
        drawHorizontalLine();
        System.out.printf("I have marked \"%s\" as done" + LINEBREAK, task.getDescription());
        drawHorizontalLine();
    }

    /**
     * @param task The deleted task
     * @param taskListSize the size of the task list after the deletion
     */
    public static void printCompleteDeleteTask(Task task, int taskListSize) {
        drawHorizontalLine();
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.printf("Now you have %d tasks in the list" + LINEBREAK, taskListSize);
        drawHorizontalLine();
    }

    /**
     * Print the DUKE logo
     */
    public static void printLogo() {
        System.out.println(LOGO);
    }

    /**
     * @param e the given error
     */
    public static void printError(Exception e) {
        if (e instanceof WrongCommandException) {
            drawHorizontalLine();
            System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            drawHorizontalLine();
        } else if (e instanceof EmptyDescriptionException) {
            drawHorizontalLine();
            System.out.println("☹ OOPS!!! The description of your command cannot be empty.");
            drawHorizontalLine();
        } else if (e instanceof MissingParameterException) {
            drawHorizontalLine();
            System.out.println("☹ OOPS!!! Your command is missing some variables.");
            drawHorizontalLine();
        } else if (e instanceof TaskNotFoundException) {
            drawHorizontalLine();
            System.out.println("☹ OOPS!!! The task you are looking for can't be found :-(");
            drawHorizontalLine();
        } else {
            drawHorizontalLine();
            System.out.println("☹ OOPS!!! Something wrong with the system");
            drawHorizontalLine();
        }
    }
}
