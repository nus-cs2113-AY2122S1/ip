public class Duke {
    static String logo = "      ____        _        \n"
            + "     |  _ \\ _   _| | _____ \n"
            + "     | | | | | | | |/ / _ \\\n"
            + "     | |_| | |_| |   <  __/\n"
            + "     |____/ \\__,_|_|\\_\\___|\n";

    static String line = "    ____________________________________________________________\n";

    // Duke greeting
    static void greet() {
        System.out.print(line);
        System.out.println(logo);
        System.out.println("     Hello! I'm Duke");
        System.out.println("     What can I do for you?");
        System.out.print(line);
    }

    // Duke exits
    static void exit() {
        System.out.println("     Bye. Hope to see you again soon!");
        System.out.print(line);
    }

    public static void main(String[] args) {
        greet();
        exit();
    }
}
