package duke;

public class Ui {

    public static final String HORIZONTAL = "____________________________________________________________\n";

    public void sayHello() {
        System.out.println(HORIZONTAL + "Hello from");
        System.out.println(" ____        _        \n" +
                "|  _ \\ _   _| | _____ \n" +
                "| | | | | | | |/ / _ \\\n" +
                "| |_| | |_| |   <  __/\n" +
                "|____/ \\__,_|_|\\_\\___");
        System.out.println(HORIZONTAL + "Hello! I'm Duke\n" + "What can I do for you?\n" + HORIZONTAL);
    }

    public void sayBye() {
        System.out.println(HORIZONTAL + "Bye. Hope to see you again soon!\n" + HORIZONTAL);
    }

    public void showLoadingError() {
        System.out.println("Unable to load");
    }

}
