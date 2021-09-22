import java.util.Scanner;

import processors.TaskList;
import processors.Ui;
import processors.ProcessManager;
import processors.Storage;
import processors.Parser;
import commands.Command;

public class Duke {
    //Initialise classes to run Duke
    public static Ui ui = new Ui();
    public static TaskList tasklist = new TaskList();
    public static ProcessManager processManager = new ProcessManager();
    public static Storage storage = new Storage();
    public static Parser parser = new Parser();
    public static Command command;

    /**
     * Main method that greets user, load their saved tasks and runs the main method that continuously
     * take in input command from user until user inputs 'bye'
     */
    public static void main(String[] args) {
        ui.welcomeMessage();
        storage.loadTasks(tasklist.taskList);

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