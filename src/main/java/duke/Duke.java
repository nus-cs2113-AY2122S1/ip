package duke;

import duke.list.ListInterface;
import duke.messages.MessageBubble;

import java.util.Scanner;

public class Duke {
    public static void run() {
        Scanner in = new Scanner(System.in);
        final String LOGO = " ____        _\n"
                + "|  _ \\ _   _| | _____\n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        MessageBubble.printMessageBubble("Hello from\n" + LOGO + "What can I do for you?");

        ListInterface.readMultipleCommands();
    }
}
