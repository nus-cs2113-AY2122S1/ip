package duke.exception;

import duke.manager.TaskManager;
import duke.ui.Ui;

public class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }

    /**
     * Prints an error message for wrong input format
     * for deadline command
     */
    public static void stringIndexDeadlineError() {
        Ui.printLine();
        System.out.println("YOU IDIOT !!??!! The description of a deadline cannot be empty.");
        Ui.printLine();
    }

    /**
     * Prints an error message for wrong input format
     * for deadline command
     */
    public static void arrayIndexDeadlineError() {
        Ui.printLine();
        System.out.println("YOU IDIOT !!??!! The input format should be : ");
        System.out.println("deadline description /by date or time");
        Ui.printLine();
    }

    /**
     * Prints an error for wrong task type
     */
    public static void taskError() {
        Ui.printLine();
        System.out.println(TaskManager.TASK_ERROR);
        Ui.printLine();
    }

    /**
     * Prints an error message for wrong date format
     */
    public static void dateTimeParseError() {
        Ui.printLine();
        System.out.println("Please enter both date and time in the format");
        System.out.println("YYYY-MM-DD HH:MM");
        Ui.printLine();
    }

    /**
     * Prints an error message for wrong input format
     */
    public static void stringIndexTodoError() {
        Ui.printLine();
        System.out.println("OOPS!!! The description of a todo cannot be empty.");
        Ui.printLine();
    }

    /**
     * Prints an error message for wrong input format
     * for done command
     */
    public static void nullPointerDoneError() {
        Ui.printLine();
        System.out.println("OH MY GOD, can you maybe type a task that exists ?");
        Ui.printLine();
    }

    /**
     * Prints an error message for wrong input format
     * for find command
     */
    public static void nullPointerFindError() {
        Ui.printLine();
        System.out.println("No tasks added yet");
        Ui.printLine();
    }

    /**
     * Prints an error message for wrong input format
     * for find command
     */
    public static void stringIndexFindError() {
        Ui.printLine();
        System.out.println("find cannot be empty");
        Ui.printLine();
    }

    /**
     * Prints an error message for wrong input format
     * for delete command
     */
    public static void indexBoundDeleteError() {
        Ui.printLine();
        System.out.println("Dude there's no task at that index");
        Ui.printLine();
    }

    /**
     * Prints an error message for wrong input format
     * for delete command
     */
    public static void nullPointerDeleteError() {
        Ui.printLine();
        System.out.println("OH MY GOD, can you maybe type things properly ?");
        System.out.println("Its delete {index}");
        Ui.printLine();
    }

    /**
     * Prints an error message for wrong input format
     * for event command
     */
    public static void stringIndexEventError() {
        Ui.printLine();
        System.out.println("YOU IDIOT !!??!! The description of an event cannot be empty.");
        Ui.printLine();
    }

    /**
     * Prints an error message for wrong input format
     * for event command
     */
    public static void arrayIndexEventError() {
        Ui.printLine();
        System.out.println("YOU IDIOT !!??!! The input format should be : ");
        System.out.println("event description /at date or time");
        Ui.printLine();
    }
}
