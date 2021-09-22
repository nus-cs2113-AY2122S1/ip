public class Ui {
    protected static void showByeScreen() {
        Duke.printMessage("Bye. Hope to see you again soon!");
    }

    protected static void showWelcomeScreen() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        Duke.printMessage("Hello from\n" + logo);
        Duke.printMessage(" Hello! I'm Duke\n" + " What can I do for you?\n");
    }
}
