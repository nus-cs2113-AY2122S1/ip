package duke;


import java.util.Scanner;

public class Ui {
    //@@author {fansxx}-reused
    //{Some Ui usage. with minor modifications}

    public static final String LINE_SEPARATOR = "____________________________________________________________";

    public static void showWelcome() {
        String logo = " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println(LINE_SEPARATOR);
    }

    public static void showBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void showLine() {
        System.out.println(LINE_SEPARATOR);
    }

    public static String readCommand() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    public static void showError(String meg) {
        System.out.println(meg);
    }

    public static void showDone() {
        System.out.println("Nice! I've marked this task as done: ");
    }

    public static void printAddedTaskMessage() {
        System.out.println("Got it. I've added this task: ");
    }

    public static void printTaskNumberMessage(int todo_index) {
        System.out.println("Now you have " + todo_index + " tasks in the list.");
    }

    public static void printDeletedTaskMessage() {
        System.out.println("Noted. I've removed this task: ");
    }

    public static void showLoadingError() {
        System.out.println("Loading error.");
    }
    public static void fileAlreadyExist() {
        System.out.println("Detected a previous saved file.");
    }


}
