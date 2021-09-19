package duke;

public class Ui {
    final private static String HORIZONTAL_LINE = "_________________________________________________________________";

    public Ui () {
        greet();
    }

    public static void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println(HORIZONTAL_LINE);
    }

    public static void showLoadingError() {
        String LOADING_ERROR = "LOAD ERROR ... LOAD ERROR ... LOAD ERROR ...";
        System.out.println(LOADING_ERROR);
    }
}
