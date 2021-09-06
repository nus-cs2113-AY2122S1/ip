package duke;

import duke.exception.EmptyDescriptionException;
import duke.exception.MissingParameterException;
import duke.exception.TaskNotFoundException;
import duke.exception.WrongCommandException;

public class DukeUI {
    private static final int DEFAULT_LINE_LENGTH = 60;
    private static final String LINEBREAK = System.lineSeparator();
    private static final String LOGO = " ____        _" + LINEBREAK
            + "|  _ \\ _   _| | _____" + LINEBREAK
            + "| | | | | | | |/ / _ \\" + LINEBREAK
            + "| |_| | |_| |   <  __/" + LINEBREAK
            + "|____/ \\__,_|_|\\_\\___|" + LINEBREAK;

    public static void greet() {
        drawHorizontalLine();
        System.out.println("Hello! I'm Duke, your personal assistant.");
        System.out.println("How can I help you?");
        drawHorizontalLine();
    }

    public static void sayGoodbye() {
        drawHorizontalLine();
        System.out.println("Bye. Hope to see you again soon!");
        drawHorizontalLine();
    }

    public static void drawHorizontalLine() {
        for (int i = 0; i < DEFAULT_LINE_LENGTH; i++) {
            System.out.print("\u2500");
        }
        System.out.println();
    }

    public static void printLogo() {
        System.out.println(LOGO);
    }

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
