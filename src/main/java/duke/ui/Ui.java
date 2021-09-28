package duke.ui;

import duke.data.Duke;
import duke.data.TaskList;
import duke.task.Task;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Text Ui of the Duke application.
 */
public class Ui {
    // Constants
    public static final String LOGO = " __     __         _       _     \n"
                                    + " \\ \\   / /        (_)     ( )    " + "  " + " ____        _        \n"
                                    + "  \\ \\_/ /   ___  ___ _ __ |/ ___ " + "  " + "|  _ \\ _   _| | _____ \n"
                                    + "   \\   / | | \\ \\/ / | '_ \\  / __|" + "  " + "| | | | | | | |/ / _ \\\n"
                                    + "    | || |_| |>  <| | | | | \\__ \\" + "  " + "| |_| | |_| |   <  __/\n"
                                    + "    |_| \\__,_/_/\\_\\_|_| |_| |___/" + "  " + "|____/ \\__,_|_|\\_\\___|\n";

    public static final String MEME = "                  ░░░░░░░▄█▄▄▄█▄░░░░░░░\n"
                                    + "                  ▄▀░░░░▄▌─▄─▄─▐▄░░░░▀▄\n"
                                    + "                  █▄▄█░░▀▌─▀─▀─▐▀░░█▄▄█\n"
                                    + "                  ░▐▌░░░░▀▀███▀▀░░░░▐▌\n"
                                    + "                  ████░▄█████████▄░████\n";

    public static final String DIVIDER = "____________________________________________________________";

    private final Scanner in;
    private final PrintStream out;

    public Ui() {
        this(System.in, System.out);
    }

    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    /**
     * Prompts for the command and reads the text entered by the user.
     *
     * @return full line command entered by the user
     */
    public String readCommand(){
        return in.nextLine();
    }

    public void showLogo(){
        System.out.println(DIVIDER + "\n" + "Hello from\n" + MEME + LOGO + DIVIDER);
    }

    public void showWelcome(){
        String greet = " Hello! I'm Yuxin's Duke\n"
                     + " What can I do for you?\n";
        System.out.println(DIVIDER + "\n" + greet + DIVIDER);
    }

    public void showLine(){
        System.out.println(DIVIDER);
    }

    public void showError(String errorInformation){
        System.out.println("OOPS!!! " + errorInformation + "  :-(");
    }

    public void showExit(){
        System.out.println("Bye. Hope to see you again soon!");
    }
}
