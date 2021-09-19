package duke.ui;

public class MessageBubble {
    static final String DIVIDER_SYMBOL = ".";
    static final int DIVIDER_SYMBOL_COUNT = 60;
    static int MAX_MESSAGES = 100;

    private final String[] messages;
    private int messagesCount;

    /**
     * Convenience Message constructor
     */
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

    /**
     * At a new line in the MessageBubble
     *
     * @param msg message to be added
     */
    public void addMessage(String msg) {
        if (msg.contains("\n")) {
            for (String line : msg.split("\n")) {
                messages[messagesCount++] = line;
            }
        } else {
            messages[messagesCount++] = msg;
        }
    }

    /**
     * Print one line of DIVIDER_SYMBOL as divider.
     * The number of DIVIDER_SYMBOL is determined by DIVIDER_SYMBOL_COUNT
     */
    static void printSectionDivider() {
        System.out.println("\t" + DIVIDER_SYMBOL.repeat(DIVIDER_SYMBOL_COUNT));
    }

    /**
     * Print the message with indentation.
     *
     * @param msg message to be printed
     */
    static void printMessage(String msg) {
        System.out.println("\t: " + msg);
    }

    /**
     * Print the messages each in one line, with indentation.
     *
     * @param msgs messages to be printed
     */
    public void printMessage(String[] msgs) {
        for (int i = 0; i < messagesCount; i++) {
            printMessage(msgs[i]);
        }
    }

    /**
     * Print the message bubble with all messages inside it.
     */
    public void printMessageBubble() {
        printSectionDivider();
        printMessage(messages);
        printSectionDivider();
    }

    /**
     * Print a message bubble with the given messages.
     *
     * @param msg messages to be printed
     */
    static public void printMessageBubble(String msg) {
        MessageBubble temp = new MessageBubble();
        temp.addMessage(msg);
        temp.printMessageBubble();
    }

    /**
     * Print the welcome message with Duke LOGO in a message bubble.
     */
    static public void printWelcomeMessage() {
        final String LOGO = " ____        _\n"
                + "|  _ \\ _   _| | _____\n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        MessageBubble.printMessageBubble("Hello from\n" + LOGO + "What can I do for you?");
    }

    /**
     * Print the farewell message in a message bubble.
     */
    static public void printByeMessage() {
        MessageBubble.printMessageBubble("Bye. Hope to see you again soon!");
    }
}
