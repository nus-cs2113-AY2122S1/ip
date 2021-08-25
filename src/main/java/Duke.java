public class Duke {

    public static void main(String[] args) {
        greetUser();
        byeUser();
    }

    // That's one dope logo
    public static void printDukeLogo() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

    public static void printLine() {
        System.out.println("____________________________________________________________");
    }
    public static void greetUser() {
        printLine();
        System.out.println("  Hello! I'm Duke\n  What can I do for you?");
        printLine();
    }
    public static void byeUser() {
        System.out.println("  Bye. Hope to see you again soon!");
        printLine();
    }
}
