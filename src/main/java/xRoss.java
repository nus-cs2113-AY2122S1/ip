public class Duke {

    // prints a divider line and new line to command line output
    public static void dividerLine(){
        System.out.println("-----------------------------------------");
    }

    // prints message and new line to command line output
    public static void printMessage(String message, boolean extra_line){
        System.out.println("\t" + message);
        if (extra_line) System.out.println();
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        dividerLine();
        printMessage("Hello! I'm Duke", false);
        printMessage("What can I do for you?", true);
        dividerLine();
        printMessage("Bye. Hope to see you again soon!", true);
        dividerLine();
    }
}
