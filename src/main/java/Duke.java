import java.util.Scanner;

public class Duke {

    private static final String LS = System.lineSeparator();
    private static final String S_TAB = "     ";

    private static final String MESSAGE_WELCOME = S_TAB + "Welcome to Jura Tempest!" + LS
                                                + S_TAB + "I'm Rimuru Tempest, pleased to make your acquaintance." + LS
                                                + S_TAB + "How can I help you today?";
    public static final String MESSAGE_GOODBYE = S_TAB + "Sayonara. Come visit our country again soon!";

    public static void main(String[] args) {
        greetUser();
        executeResponse();
    }

    /**
     * Prints a message that greets the user.
     */
    public static void greetUser() {
        Picture.drawRimuruLogo();
        System.out.println(MESSAGE_WELCOME);
        Picture.printLine();
    }

    /**
     * Prints an exit message.
     */
    public static void exitDuke() {
        Picture.printLine();
        System.out.println(MESSAGE_GOODBYE);
        Picture.printLine();
    }

    /**
     * Continually waits for input user commands,
     * and executes the corresponding actions,
     * until the exit command is typed.
     */
    public static void executeResponse() {
        String line;
        boolean isExit = false;
        Scanner in = new Scanner(System.in);
        do {
            line = in.nextLine();
            String[] words = line.split(" ");
            switch (words[0]) {
            case "bye":
                exitDuke();
                isExit = true;
                break;
            case "list":
                TaskManager.printList();
                break;
            case "done":
                TaskManager.markAsCompleted(Integer.parseInt(words[1]));
                break;
            case "todo":
                TaskManager.addToList(line, TaskType.TODO);
                break;
            case "deadline":
                TaskManager.addToList(line, TaskType.DEADLINE);
                break;
            case "event":
                TaskManager.addToList(line, TaskType.EVENT);
                break;
            case "help":
                TaskManager.printHelpMessage();
                break;
            default:
                TaskManager.printInvalidCommandMessage();
            }
        } while (!isExit);
    }

}
