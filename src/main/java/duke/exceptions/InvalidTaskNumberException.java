package main.java.duke.exceptions;

import main.java.duke.ui.Ui;

/**
 * Custom exception to catch when user input is missing the task number
 * parameter or if the task number provided is invalid where it is out
 * of range of the size of the task list.
 */
public class InvalidTaskNumberException extends Throwable {

    public static void sendErrorMessage() {
        System.out.println(Ui.HORIZONTAL_LINE_TOP
                + "\n ☹ OOPS!!! Please input a valid task number.\n"
                + Ui.HORIZONTAL_LINE_BOTTOM);
    }

}
