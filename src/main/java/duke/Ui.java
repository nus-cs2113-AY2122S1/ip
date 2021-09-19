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

    public String getInput() {
        return in.nextLine();
    }

    public void showExceptionMessage(Exception ex) {
        System.out.println(ex.getMessage());
    }

    public void showGoodByeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}
