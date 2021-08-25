import java.util.Scanner;

/**
 * The Duke application serves to aid users in the management of their tasks.
 */
public class Duke {

    /* Hard-coded line */
    private final static String LINE = "____________________________________________________________";

    /* Name of chat-bot */
    private final static String BOT_NAME = "taskmon";

    /* List of support commands */
    private final static String END_COMMAND = "bye";
    private final static String LIST_COMMAND = "list";
    private final static String DONE_COMMAND = "done";

    /* Indexes used for parsing of commands */
    private final static int COMMAND_INDEX = 0;
    private final static int TASK_NUMBER_INDEX = 1;

    /* List of chat-bot messages */
    private final static String WELCOME_MESSAGE = ""
            + "[*] Detecting chat-bot version...\n"
            + "[+] Chat-bot version is *VULNERABLE*!\n"
            + "[*] Hacking into the chat-bot...\n"
            + "[*] Escalating privileges...\n"
            + "[+] Interactive shell spawned. You can now manipulate the chat-bot directly!";
    private final static String BYE_MESSAGE = "[*] Deleting traces of compromise...\n"
            + "[+] Bye. Hope to see you again soon!";

    /* Storage of tasks */
    private static TaskManager taskManager = new TaskManager();

    /* State of whether interaction has terminated. True if interaction has terminated. */
    private static boolean isEnd = false;

    /**
     * Prints the given string in between 2 horizontal lines.
     *
     * @param section The string to be printed.
     */
    private static void printSection(String section) {
        System.out.println(LINE);
        System.out.println(section);
        System.out.println(LINE);
    }

    /**
     * Parses user command and perform the necessary actions.
     *
     * @param input The command used to decide the action to perform.
     */
    private static void handleInput(String input) {
        String[] tokens = input.stripTrailing().split(" ");

        try {
            switch (tokens[COMMAND_INDEX]) {
            case LIST_COMMAND:
                taskManager.printTaskList();
                break;
            case DONE_COMMAND:
                taskManager.complete(Integer.parseInt(tokens[TASK_NUMBER_INDEX]));
                break;
            case END_COMMAND:
                isEnd = true;
                break;
            case "":
                break;
            default:
                taskManager.addTask(input);
                break;
            }
        } catch (ArrayIndexOutOfBoundsException err) {
            System.out.println("[-] Missing arguments!");
        } catch (NumberFormatException err) {
            System.out.println("[-] Invalid arguments!");
        }
    }

    /**
     * Provides an interactive prompt to the user.
     */
    private static void interact() {
        String rawLine;
        Scanner in = new Scanner(System.in);
        printSection(WELCOME_MESSAGE);

        do {
            System.out.printf("[root@%s ~]$ ", BOT_NAME);
            rawLine = in.nextLine();
            handleInput(rawLine);
        } while (!isEnd);

        printSection(BYE_MESSAGE);
    }

    public static void main(String[] args) {
        interact();
    }
}
