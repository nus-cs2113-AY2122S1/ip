import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * This represents the Duke Chat bot.
 */
public class Duke {

    private static boolean isDukeDone = false;
    private static Task[] list = new Task[100];
    private static int listIndexTracker = 0;
    private static boolean isInvalidInput = false;

    private final static String LINES = "    ____________________________________________________________";
    private final static String TAB = "    ";
    private final static String GREETING = "    Hello... I'm Sadge Duke\n    What can I do for you? :(";
    private final static String GOODBYE = "    Bye. I'll miss you...";
    private final static String TODO = "todo";
    private final static String DEADLINE = "deadline";
    private final static String EVENT = "event";
    private final static int DESCRIPTION = 0;
    private final static int DATETIME = 1;


    /**
     * Main function that is called upon program execution.
     *
     * @param args System Arguments added to program.
     */
    public static void main(String[] args) {
        printWelcomeBanner();

        Scanner in = new Scanner(System.in);
        while (!isDukeDone) {
            String input = in.nextLine();
            parseInput(input);
        }


    }

    /**
     * This function parses the input given by user and prints the appropriate response.
     *
     * @param input input given by the user.
     */
    private static void parseInput(String input) {
        System.out.println(LINES);
        if (input.equalsIgnoreCase("Bye")) {
            System.out.println(GOODBYE);
            setDukeDone();
        } else if (input.equalsIgnoreCase("List")) {
            printList();
        } else if (isValidDoneInput(input)) {
            handleDoneInput(input);
        } else if (isValidTodoInput(input)) {
            addToList(input, TODO);
        } else if (isValidDeadlineInput(input)) {
            addToList(input, DEADLINE);
        } else if (isValidEventInput(input)) {
            addToList(input, EVENT);
        } else {
            setInvalidInput(true);
        }
        if (isInvalidInput) {
            System.out.println(TAB + "Invalid input");
            setInvalidInput(false);
        }
        System.out.println(LINES);
    }


    /**
     * This function adds the input description into list accordingly, based on its task type.
     *
     * @param input input given by the user.
     * @param taskType task type identified beforehand (DEADLINE/EVENT/TODO).
     */
    private static void addToList(String input, String taskType) {
        String[] parameters = new String[2];
        getParameters(parameters, input, taskType);
        switch (taskType) {
        case TODO:
            getParameters(parameters, input, TODO);
            list[listIndexTracker] = new Todo(parameters[DESCRIPTION]);
            break;
        case DEADLINE:
            getParameters(parameters, input, DEADLINE);
            list[listIndexTracker] = new Deadline(parameters[DESCRIPTION], parameters[DATETIME]);
            break;
        case EVENT:
            getParameters(parameters, input, EVENT);
            list[listIndexTracker] = new Event(parameters[DESCRIPTION], parameters[DATETIME]);
            break;
        }

        listIndexTracker++;
        printAddedMessage();
    }

    /**
     * This function prints the message when task is added to list.
     */
    private static void printAddedMessage() {
        System.out.println(TAB + "Got it. I've added this task: ");
        System.out.println(TAB + TAB + list[listIndexTracker - 1]);
        System.out.println(TAB + String.format("Now you have %d tasks in the list.", listIndexTracker));
    }

    /**
     * This function handles the done input by marking task as done.
     * @param input input given by the user.
     */
    private static void handleDoneInput(String input) {
        int index = getDoneIndex(input);
        list[index].markAsDone();
        System.out.print(TAB + "Nice! I've marked this task as done:" + System.lineSeparator() + TAB + TAB);
        System.out.println(list[index]);
    }

    /**
     * This function prints the individual elements in list.
     */
    private static void printList() {
        for (int i = 0; i < listIndexTracker; i++) {
            System.out.format(TAB + "% 3d.", i + 1);
            System.out.println(list[i]);
        }
    }

    /**
     * This function sets isDukeDone to true, stopping while loop and signifying the end of program.
     */
    private static void setDukeDone() {
        isDukeDone = true;
    }

    /**
     * This function check if done statement and index in done statement is valid.
     * @param input input given by the user.
     * @return returns the validity of the done input.
     */
    private static boolean isValidDoneInput(String input) {
        boolean validDoneStatement = Pattern.matches("^done \\d+$", input.toLowerCase());
        if (!validDoneStatement) {
            return false;
        }
        int numberOnly = Integer.parseInt(input.replaceAll("[^0-9]", ""));
        boolean numberWithinCurrMax = numberOnly <= listIndexTracker;
        boolean numberWithinPossMax = numberOnly < 100;
        return  numberWithinCurrMax && numberWithinPossMax;
    }

    /**
     * This function retrieves the index given in done statement.
     * @param input input given by the user.
     * @return returns the index given in done statement
     */
    private static int getDoneIndex(String input) {
        String[] parts = input.split(" ");
        return Integer.parseInt(parts[1]) - 1;
    }

    /**
     * This function sets error as the given boolean argument.
     * @param validity boolean to set isInvalidInput as.
     */
    private static void setInvalidInput(boolean validity) {
        isInvalidInput = validity;
    }

    /**
     * This function uses regex to check if to do statement is valid.
     * @param input input given by the user.
     * @return returns validity of the to do statement.
     */
    private static boolean isValidTodoInput(String input) {
        return Pattern.matches("todo [:a-z0-9\\s]+", input.toLowerCase());
    }

    /**
     * This function uses regex to check if deadline statement is valid.
     * @param input input given by the user.
     * @return returns validity of the deadline statement.
     */
    private static boolean isValidDeadlineInput(String input) {
        return Pattern.matches("deadline [a-z0-9\\s]+\\b /by .+", input.toLowerCase());
    }

    /**
     * This function uses regex to check if event statement is valid.
     * @param input input given by the user.
     * @return returns validity of the event statement.
     */
    private static boolean isValidEventInput(String input) {
        return Pattern.matches("event [a-z0-9\\s]+\\b /at .+", input.toLowerCase());
    }

    /**
     * This function gets the parameters for the Task subclasses by slicing input.
     * @param parameters Array of string of fixed size 2 to store parameters for Task subclasses.
     * @param input User's input into command line.
     * @param taskType String that is pre-identified (DEADLINE/EVENT/TODO)
     */
    private static void getParameters(String[] parameters, String input, String taskType) {
        switch (taskType) {
        case TODO:
            String[] todoParts = input.split("(?i)todo ");
            parameters[DESCRIPTION] = todoParts[1];
            break;
        case DEADLINE:
            String[] initDeadlineParts = input.split("(?i)deadline ");
            String[] deadlineParts = initDeadlineParts[1].split(" /by ");
            parameters[DESCRIPTION] = deadlineParts[0];
            parameters[DATETIME] = deadlineParts[1];
            break;
        case EVENT:
            String[] initEventParts = input.split("(?i)event ");
            String[] eventParts = initEventParts[1].split(" /at ");
            parameters[DESCRIPTION] = eventParts[0];
            parameters[DATETIME] = eventParts[1];
            break;
        }
    }

    /**
     * This function prints the welcome banner.
     */
    private static void printWelcomeBanner() {
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
    }
}

