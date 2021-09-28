package duke.exception;

import duke.manager.TaskManager;
import duke.ui.Ui;

public class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }

    public static void stringIndexDeadlineError() {
        Ui.printLine();
        System.out.println("YOU IDIOT !!??!! The description of a deadline cannot be empty.");
        Ui.printLine();
    }

    public static void arrayIndexDeadlineError() {
        Ui.printLine();
        System.out.println("YOU IDIOT !!??!! The input format should be : ");
        System.out.println("deadline description /by date or time");
        Ui.printLine();
    }


    public static void stringIndexEventError() {
        Ui.printLine();
        System.out.println("YOU IDIOT !!??!! The description of an event cannot be empty.");
        Ui.printLine();
    }

    public static void arrayIndexEventError() {
        Ui.printLine();
        System.out.println("YOU IDIOT !!??!! The input format should be : ");
        System.out.println("event description /on date or time");
        Ui.printLine();
    }
}
