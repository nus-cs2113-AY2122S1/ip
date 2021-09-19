package duke.ui;

public class DukePredefinedMessages {
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
