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
        messages[messagesCount++] = msg;
    }

    static void printSectionDivider() {
        System.out.println("    " + DIVIDER_SYMBOL.repeat(DIVIDER_SYMBOL_COUNT));
    }

    static void printMessage(String msg) {
        System.out.println("     " + msg);
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

    static void printMessageBubble(String msg) {
        printSectionDivider();
        printMessage(msg);
        printSectionDivider();
    }
}
