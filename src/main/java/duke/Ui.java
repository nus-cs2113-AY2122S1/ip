package duke;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static duke.message.Messages.BYE;
import static duke.message.Messages.DELETED_TASK;
import static duke.message.Messages.ERROR;
import static duke.message.Messages.FILE_NOT_FOUND;
import static duke.message.Messages.FINISHED_TASK;
import static duke.message.Messages.LIST_TASK;
import static duke.message.Messages.LOGO;
import static duke.message.Messages.WELCOME;

public class Ui {
    private static final String SEPARATOR = "zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz";
    private static final String LS = System.lineSeparator();
    private static final String INPUT = "input: ";

    private final Scanner in;
    private final PrintStream out;

    public Ui() {
        this(System.in, System.out);
    }

    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    public void printWelcome() {
        out.println(LOGO);
        out.println(WELCOME);
    }

    public void printFileNotFound(String e) {
        out.println(e + FILE_NOT_FOUND + LS + SEPARATOR);
    }

    public void printError(String e) {
        out.println(ERROR + e);
    }

    public void printDukeException(String e) {
        out.println(e);
    }

    public void printListTask() {
        out.println(SEPARATOR + LS + LIST_TASK);
    }

    public void printToUser(String... message) {
        for (String m : message) {
            out.print(m);
        }
        out.println();
    }

    public void printFinishedTask() {
        out.println(SEPARATOR + LS + FINISHED_TASK);
    }

    public void printDeletedTask() {
        out.println(SEPARATOR + LS + DELETED_TASK);
    }

    public void printBye() {
        out.println(BYE);
    }

    public String getUserInput() {
        out.print(SEPARATOR + LS + INPUT);

        return in.nextLine();
    }
}
