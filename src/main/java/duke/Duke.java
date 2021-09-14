package duke;

import duke.list.TaskList;
import duke.task.Parser;
import duke.ui.MessageBubble;
import duke.file.Storage;

import java.util.Scanner;

public class Duke {
    /**
     * Start running Duke CLI client
     */
    public static void run() {
        MessageBubble.printWelcomeMessage();
        TaskList taskList = Storage.loadFile(); // load saved data
        Scanner input = new Scanner(System.in);
        boolean isExit = false;
        while (!isExit) {
            String command = input.nextLine();
            Parser result = new Parser();
            result.parse(command, taskList);
            isExit = result.isExit;

            // save file after each command
            Storage.saveFile(taskList);
        }

        MessageBubble.printByeMessage();
    }
}
