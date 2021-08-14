public class Duke {

    public static void main(String[] args) {
        greetUser();
        exitDuke();
    }

    // Template code to check if editor is working
    public static void printDukeLogo() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

    // Greeting message
    public static void greetUser() {
        String hLine = "____________________________________________________________\n";
        String wcMsg = " Hello! I'm Duke\n" + " What can I do for you?\n";
        System.out.print(hLine + wcMsg);
    }

    // Exit message
    public static void exitDuke() {
        String hLine = "____________________________________________________________\n";
        String exitMsg = " Bye. Hope to see you again soon!";
        System.out.print(hLine + exitMsg + "\n" + hLine);
    }
}
