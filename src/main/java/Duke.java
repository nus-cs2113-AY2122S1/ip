public class Duke {
    public static void main(String[] args) {
        printStartMessage();
        printExitMessage();
    }

    public static void printAppLogo() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

    public static void printLine() {
        System.out.println("______________________________________");
    }

    public static void printStartMessage() {
        printLine();
        System.out.println("Hello! I'm Duke\n How may I assist you");
        printLine();
    }

    public static void printExitMessage() {
        System.out.println("Goodbye! Hope to see you soon!");
        printLine();
    }
}
