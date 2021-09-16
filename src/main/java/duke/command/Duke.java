package duke.command;

import duke.task.TaskManager;
import java.io.IOException;
import java.util.Scanner;


public class Duke {
    public static void main(String[] args) throws IOException {
        TaskManager taskManager = new TaskManager();
        Scanner scanner = new Scanner(System.in);
        Ui ui = new Ui(taskManager, scanner);
        ui.start();
    }
}
