public class Ui {
    protected static void showByeScreen() {
        System.out.println("Bye. Hope to see you again soon!");
        Duke.printDivider();
    }

    protected static void showWelcomeScreen() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println(" Hello! I'm Duke\n" + " What can I do for you?\n");
        Duke.printDivider();
    }
}
