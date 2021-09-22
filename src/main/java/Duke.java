import java.util.Scanner;

import processors.TaskList;
import processors.Ui;
import processors.ProcessManager;
import processors.Storage;
import processors.Parser;
import commands.Command;

public class Duke {
    public static Ui ui = new Ui();
    public static TaskList tasklist = new TaskList();
    public static ProcessManager processManager = new ProcessManager();
    public static Storage storage = new Storage();
    public static Parser parser = new Parser();
    public static Command command;

    public static void main(String[] args) {
        ui.welcomeMessage();
        storage.loadTasks(tasklist);

        String line;
        Scanner in = new Scanner(System.in);
        boolean isProgress = true;

        while (isProgress) {
            line = in.nextLine();
            line = line.trim();

            command = parser.decipher(line);
            isProgress = processManager.executeCommand(command, tasklist, line);
        }
    }
}