package duke.program;

public class Duke {
    private static final int LINE_LENGTH = 40;

    public static void printGreetingMessage(LizTextBanner liz) {
        System.out.println("Howdy! It's\n" + liz.getLizText() + liz.getLizLogo());
        printLine();
        System.out.println("Hey! I'm Lizzy the Lizard!");
        System.out.println("What can I do for you?");
        System.out.println("");
    }

    public static void printExitMessage() {
        printLine();
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void printLine() {
        for (int i = 0; i < LINE_LENGTH; i++) {
            System.out.print("_");
        }
        System.out.println("");
    }

    public static void readInputs(TaskManager manager) {
        manager.parseUserInput();
    }

    public static void main(String[] args) {

        LizTextBanner liz = new LizTextBanner();
        TaskManager manager = new TaskManager();

        printGreetingMessage(liz);
        readInputs(manager);
        printExitMessage();
    }
}
