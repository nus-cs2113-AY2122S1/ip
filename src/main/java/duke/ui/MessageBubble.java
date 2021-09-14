package duke.ui;

public class MessageBubble {
    static final String DIVIDER_SYMBOL = "_";
    static final int DIVIDER_SYMBOL_COUNT = 60;
    static int MAX_MESSAGES = 100;

    private String[] messages;
    private int messagesCount;

    public MessageBubble() {
        messages = new String[MAX_MESSAGES];
        messagesCount = 0;
    }

    public MessageBubble(String msg) {
        this();
        addMessage(msg);
    }

    public MessageBubble(String[] msgs) {
        this();
        for (String msg : msgs) {
            addMessage(msg);
        }
    }

    public void addMessage(String msg) {
        if (msg.contains("\n")) {
            for (String line : msg.split("\n")) {
                messages[messagesCount++] = line;
            }
        } else {
            messages[messagesCount++] = msg;
        }
    }

    static void printSectionDivider() {
        System.out.println("\t" + DIVIDER_SYMBOL.repeat(DIVIDER_SYMBOL_COUNT));
    }

    static void printMessage(String msg) {
        System.out.println("\t " + msg);
    }

    public void printMessage(String[] msgs) {
        for (int i = 0; i < messagesCount; i++) {
            printMessage(msgs[i]);
        }
    }

    public void printMessageBubble() {
        printSectionDivider();
        printMessage(messages);
        printSectionDivider();
    }

    static public void printMessageBubble(String msg) {
        MessageBubble temp = new MessageBubble();
        temp.addMessage(msg);
        temp.printMessageBubble();
    }

    static public void printWelcomeMessage() {
        final String LOGO = " ____        _\n"
                + "|  _ \\ _   _| | _____\n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        MessageBubble.printMessageBubble("Hello from\n" + LOGO + "What can I do for you?");
    }

    static public void printByeMessage() {
        MessageBubble.printMessageBubble("Bye. Hope to see you again soon!");
    }
}
