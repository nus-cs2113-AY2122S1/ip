package duke.ui;

import duke.DukeException;
import duke.command.CommandException;
import duke.parser.ParserException;
import java.util.Scanner;

public class Ui {

    final static String LINE = "____________________________________________________________";

    private Scanner in;

    public Ui() {
        in = new Scanner(System.in);
    }

    public void printExitMessage() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(LINE);
    }

    public void printWelcomeMessage() {
        System.out.printf("%s\n", getLogoMessage());
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println(LINE);
    }

    private static String getLogoMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        return logo;
    }

    public String getUserInput() {
        System.out.printf(">>> ");
        return in.nextLine();
    }

    public void printMessage(String... data) {
        for (String s : data) {
            System.out.println(s);
        }
        System.out.println(LINE);
    }

    public void printDukeExceptionMessage(DukeException e) {
        System.out.println(e.toString());
        System.out.println(LINE);
    }

    public void printParserExceptionMessage(ParserException e) {
        System.out.println(e.toString());
        System.out.println(LINE);
    }

    public void printCommandExceptionMessage(CommandException e) {
        System.out.println(e.toString());
        System.out.println(LINE);
    }

    public void printFileLoadingMessage(String filename) {
        System.out.printf("Loading data from %s...\n", filename);
        System.out.println(LINE);
    }

    public void printFileLoadingDoneMessage() {
        System.out.println("Finish Loading.");
        System.out.println(LINE);
    }

    public void printLine() {
        System.out.println(LINE);
    }

}
