public class Duke {

    private static final String line = "____________________________________________________________";

    public static void main(String[] args) {
        System.out.println(line);
        greetUser();
        byeDuke();
    }

    public static void printLogo() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

    // greet the user and prints a line
    public static void greetUser() {
        System.out.println(" Hello! I'm Duke\n" +
                " What can I do for you?");
        System.out.println(line);
    }

    // exits the programme after printing the greeting and a line
    public static void byeDuke() {
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println(line);
    }
}
