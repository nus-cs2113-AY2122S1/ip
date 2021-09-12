package duke;

import java.util.Scanner;
import duke.command.CommandExecutor;

/**
 * The Duke application serves to aid users in the management of their tasks.
 */
public class Duke {

    /* Hard-coded line */
    private final static String LINE = "____________________________________________________________";

    /* Name of chat-bot */
    private final static String BOT_NAME = "taskmon";

    /* List of chat-bot messages */
    private final static String WELCOME_MESSAGE = ""
            + "[*] Detecting chat-bot version...\n"
            + "[+] Chat-bot version is *VULNERABLE*!\n"
            + "[*] Hacking into the chat-bot...\n"
            + "[*] Escalating privileges...\n"
            + "[+] Interactive shell spawned. You can now manipulate the chat-bot directly!";
    private final static String BYE_MESSAGE = "[*] Deleting traces of compromise...\n"
            + "[+] Bye. Hope to see you again soon!";

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
     * Provides an interactive prompt to the user.
     */
    private static void interact() {
        String rawLine;
        Scanner in = new Scanner(System.in);
        CommandExecutor commandExecutor = new CommandExecutor();
        printSection(WELCOME_MESSAGE);

        do {
            System.out.printf("[root@%s ~]$ ", BOT_NAME);
            rawLine = in.nextLine();
            commandExecutor.execute(rawLine);
        } while (!commandExecutor.isExit());

        printSection(BYE_MESSAGE);
    }

    public static void main(String[] args) {
        interact();
    }
}
