
import todo.*;

import java.util.ArrayList;
/**
 * Check if valid command (Done, List, Bye, Action)
 * If done, mark as done
 * If action, check what action is it
 * If there is a date, what is the date
 *
 */


public class Duke {
    protected static ArrayList<Task> tasks = new ArrayList<>();
    protected static ArrayList<String> commands = new ArrayList<>();
    protected static String filePath;
    public static void main(String[] args) {
        new Duke("data.txt").run();
    }

    protected static void run() {
        Ui.showWelcomeScreen();
        addCommands();
        Storage.readFile();
        String line = "";
        Parser.readInput(line);
        return;
    }

    public Duke(String filePath) {
        this.filePath = filePath;
    }

    protected static void addCommands() {
        String[] commandsToAdd = new String[]{"done", "todo", "event",
                    "deadline", "delete", "list", "save", "bye", "find"};
        for(int i = 0; i < commandsToAdd.length; i++) {
            commands.add(commandsToAdd[i]);
        }
    }


    public final static void printMessage(String text) {
        System.out.println(text);
        printDivider();
    }

    public final static void printDivider() {
        System.out.println("____________________________________________________________\n");
    }

}

