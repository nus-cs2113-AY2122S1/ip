import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        printSection("Hello! I'm Duke", "What can I do for you?");

        // Initialize a scanner to read in user input
        Scanner scanner = new Scanner(System.in);

        // Keep listening for new commands
        while (true) {
            // Read the input of the user
            System.out.print("> ");
            String cmd = scanner.nextLine();
            System.out.println();

            // Check if the user wishes to exit the program.
            if (!parseCommands(cmd)) {
                break;
            }

            // Otherwise, ask the user for more commands
            System.out.println("--");
            System.out.println("Anything else I can help you with?");
        }

        // Cleanup and print exit message
        printSection("Bye. Hope to see you again soon!");
    }

    /**
     * Prints the bot name and given data provided.
     * If no data is provided, it will print a horizontal line with the bot name.
     *
     * @param data The string(s) to print to console.
     */
    private static void printSection(String ...data) {
        System.out.println("--// Duke //-----------------------------------------------");
        for (String s: data) {
            System.out.println(s);
        }
    }

    /**
     * Interprets the commands sent by the user.
     *
     * @param cmd The command sent by the user.
     * @return Returns true if the program should continue executing.
     */
    private static boolean parseCommands(String cmd) {
        switch (cmd.toLowerCase()) {
        case "bye":
            return false;
        default:
            printSection(cmd);
            return true;
        }
    }
}
