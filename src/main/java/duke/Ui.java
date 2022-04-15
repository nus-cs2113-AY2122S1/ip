package duke;

public class Ui {

    public static final String HORIZONTAL = "____________________________________________________________\n";

    /**
     * Shows the welcome message for Duke.
     */
    public void sayHello() {
        System.out.println(HORIZONTAL + "Hello from");
        System.out.println(" ____        _        \n" +
                "|  _ \\ _   _| | _____ \n" +
                "| | | | | | | |/ / _ \\\n" +
                "| |_| | |_| |   <  __/\n" +
                "|____/ \\__,_|_|\\_\\___");
        System.out.println(HORIZONTAL + "Hello! I'm Duke\n" + "What can I do for you?\n" + HORIZONTAL);
    }

    /**
     * Shows the exit message for Duke.
     */
    public void sayBye() {
        System.out.println(HORIZONTAL + "Bye. Hope to see you again soon!\n" + HORIZONTAL);
    }
}
