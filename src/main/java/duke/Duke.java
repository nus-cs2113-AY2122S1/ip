package duke;

import duke.exceptions.EmptyField;
import duke.exceptions.IllegalOperation;
import duke.list.List;
import duke.list.ListInterface;
import duke.messages.MessageBubble;
import duke.file.Storage;

import java.io.File;
import java.io.IOException;

public class Duke {
    public static void run() {
        final String LOGO = " ____        _\n"
                + "|  _ \\ _   _| | _____\n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        MessageBubble.printMessageBubble("Hello from\n" + LOGO + "What can I do for you?");

        List localData = Storage.loadFile();
        ListInterface.readMultipleCommands(localData);
    }
}
