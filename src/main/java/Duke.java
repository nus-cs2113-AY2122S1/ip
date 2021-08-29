import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * This represents the Duke Chat bot.
 */
public class Duke {

    public static boolean done = false;
    public static Task[] list = new Task[100];
    public static int listIndexTracker = 0;
    private final static String LINES = "    ____________________________________________________________";
    private final static String TAB = "    ";
    private final static String TASKFORMAT = "         ";
    private final static String GREETING = "    Hello... I'm Sadge Duke\n    What can I do for you? :(";
    private final static String GOODBYE = "    Bye. Hope to see you again soon!";

    /**
     * Main function that is called upon program execution.
     *
     * @param args System Arguments added to program.
     */
    public static void main(String[] args) {

        /**
         * ASCII art source: https://www.twitchquotes.com/copypastas/2059
         */
        String sadge = "   ⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠿⠛⠋⠉⣉⣉⠙⠿⠋⣠⢴⣊⣙⢿⣿⣿\n"
                + "   ⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⠟⠋⠁⠀⢀⠔⡩⠔⠒⠛⠧⣾⠊⢁⣀⣀⣀⡙⣿\n"
                + "   ⣿⣿⣿⣿⣿⣿⣿⠟⠛⠁⠀⠀⠀⠀⠀⡡⠊⠀⠀⣀⣠⣤⣌⣾⣿⠏⠀⡈⢿⡜\n"
                + "   ⣿⣿⣿⠿⠛⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠡⣤⣶⠏⢁⠈⢻⡏⠙⠛⠀⣀⣁⣤⢢\n"
                + "   ⣿⠋⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠰⣄⡀⠣⣌⡙⠀⣘⡁⠜⠈⠑⢮⡭⠴⠚⠉⠀\n"
                + "   ⠁⠀⢀⠔⠁⣀⣤⣤⣤⣤⣤⣄⣀⠀⠉⠉⠉⠉⠉⠁⠀⠀⠀⠀⠀⠁⠀⢀⣠⢠\n"
                + "   ⡀⠀⢸⠀⢼⣿⣿⣶⣭⣭⣭⣟⣛⣛⡿⠷⠶⠶⢶⣶⣤⣤⣤⣶⣶⣾⡿⠿⣫⣾\n"
                + "   ⠇⠀⠀⠀⠈⠉⠉⠉⠉⠉⠙⠛⠛⠻⠿⠿⠿⠷⣶⣶⣶⣶⣶⣶⣶⣶⡾⢗⣿⣿\n"
                + "   ⣦⣄⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣠⣴⣿⣶⣾⣿⣿⣿\n"
                + "   ⣿⣿⣿⣷⣶⣤⣄⣀⣀⣀⡀⠀⠀⠀⠀⠀⠀⢀⣀⣤⣝⡻⣿⣿⣿⣿⣿⣿⣿⣿\n"
                + "   ⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣦⡹⣿⣿⣿⣿⣿⣿\n";

        /**
         * ASCII art source: https://fsymbols.com/text-art/
         */
        String text = "█▀ ▄▀█ █▀▄ █▀▀ █▀▀   █▀▄ █░█ █▄▀ █▀▀\n"
                + "▄█ █▀█ █▄▀ █▄█ ██▄   █▄▀ █▄█ █░█ ██▄";

        System.out.println("Hello from\n" + sadge + text);

        System.out.println(LINES);
        System.out.println(GREETING);
        System.out.println(LINES);

        Scanner in = new Scanner(System.in);
        while (!done) {
            String input = in.nextLine();
            parseInput(input);
        }


    }

    /**
     * This function parses the input given by user and prints the appropriate response.
     *
     * @param input input given by the user.
     */
    public static void parseInput(String input) {
        System.out.println(LINES);
        boolean error = false;

        if (input.equalsIgnoreCase("Bye")) {
            System.out.println(GOODBYE);
            done = true;
        } else if (input.equalsIgnoreCase("List")) {
            printList();
        } else if (Pattern.matches("^done \\d+$", input.toLowerCase())) {
            String[] parts = input.split(" ");
            int index = Integer.parseInt(parts[1]) - 1;
            if (index < listIndexTracker) {
                list[index].markAsDone();
                System.out.println(TAB + "Nice! I've marked this task as done:");
                System.out.format(TASKFORMAT + "[%s] %s%n", list[index].getStatusIcon(), list[index].getDescription());
            } else {
                error = true;
            }
        } else {
            addToList(input);
            System.out.print(TAB + "added: ");
            System.out.println(input);
        }
        if (error) {
            System.out.println(TAB + "Invalid input");
        }
        System.out.println(LINES);
    }

    /**
     * This function adds the input string into list
     *
     * @param input input given by the user.
     */
    public static void addToList(String input) {
        list[listIndexTracker] = new Task(input);
        listIndexTracker++;
    }

    /**
     * This function prints the individual elements in list
     */
    public static void printList() {
        for (int i = 0; i < listIndexTracker; i++) {
            System.out.format(TAB + "% 3d. [%s]", i + 1, list[i].getStatusIcon());
            System.out.println(list[i].getDescription());
        }
    }
}
