
public class Duke {

    //TODO Abstract repeated functions
    // bugs: should use startswith for command messages?? or how to extract the command keyword?

    public static void main(String[] args) {
        welcomeMessage();
        CommandManager.executeCommand();
    }

    public static void printlnTab(String str) {
        System.out.println("\t" + str);
    }

    public static void printDivider() {
        System.out.println("\t____________________________________________________________");
        System.out.println();
    }

    public static void welcomeMessage() {
        String logo = "\t ____        _        \n"
                + "\t|  _ \\ _   _| | _____ \n"
                + "\t| | | | | | | |/ / _ \\\n"
                + "\t| |_| | |_| |   <  __/\n"
                + "\t|____/ \\__,_|_|\\_\\___|\n";

        printDivider();
        System.out.println(logo);
        printlnTab("Hello! I'm Duke\n\tWhat can I do for you?");
        printDivider();
    }


}
