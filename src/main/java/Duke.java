public class Duke {

    public static void printHorizontal() {
        System.out.println("____________________________________________________________");
    }

    public static void greetUser() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        printHorizontal();
        System.out.println("Welcome to\n" + logo);
        System.out.println("Hello there! I'm Duke, your personal assistant chat bot.\n"
                + "What can I do for you today?");
        printHorizontal();
    }

    public static void exitUser() {
        System.out.println("Bye! See you again soon. \uD83D\uDE04");
        printHorizontal();
    }

    public static void main(String[] args) {
        greetUser();
        exitUser();
    }
}
