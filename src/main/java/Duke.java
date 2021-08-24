import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * This represents the Duke Chat bot.
 */
public class Duke {

    /**
     * boolean done signifies whether the program is done running.
     */
    public static boolean done = false;
    /**
     * String[] list stores the commands input by user. It has a fixed size of 100.
     */
    public static Task[] list = new Task[100];
    /**
     * int listIndexTracker tracks the index for list to add elements in.
     */
    public static int listIndexTracker = 0;

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

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + sadge + text);

        System.out.println(lines());
        System.out.println(greeting());
        System.out.println(lines());

        Scanner in = new Scanner(System.in);
        while (!done) {
            String input = in.nextLine();
            parseInput(input);
        }


    }

    /**
     * This function prints some lines.
     */
    public static String lines() {
        return "    ____________________________________________________________";
    }

    /**
     * This function prints a greeting message.
     */
    public static String greeting() {
        return "    Hello... I'm Sadge Duke\n    What can I do for you? :(";
    }

    /**
     * This function prints a bye message.
     */
    public static String bye() {
        return "    Bye. Hope to see you again soon!";
    }

    /**
     * This function parses the input given by user and prints the appropriate response.
     *
     * @param input input given by the user.
     */
    public static void parseInput(String input) {
        System.out.println(lines());
        boolean error = false;

        if (input.equalsIgnoreCase("Bye")) {
            System.out.println(bye());
            done = true;
        } else if (input.equalsIgnoreCase("List")) {
            printList();
        } else if (Pattern.matches("^done \\d+$", input.toLowerCase())) {
            String[] parts = input.split(" ");
            int index = Integer.parseInt(parts[1]) - 1;
            if (index < listIndexTracker) {
                list[index].markAsDone();
            } else {
                error = true;
            }
        } else {
            addToList(input);
            System.out.print("    added: ");
            System.out.println(input);
        }
        if (error) {
            System.out.println("Invalid input");
        }
        System.out.println(lines());
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
            System.out.format("    % 3d. [%s]", i + 1, list[i].getStatusIcon());
            System.out.println(list[i].getDescription());
        }
    }
}
