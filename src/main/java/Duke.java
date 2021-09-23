
import todo.*;

import java.util.ArrayList;

//Check if valid command (Done, List, Bye, Action)
//If done, mark as done
//If action, check what action is it
//If there is a date, what is the date

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
        commands.add("done");
        commands.add("todo");
        commands.add("event");
        commands.add("deadline");
        commands.add("delete");
        commands.add("list");
        commands.add("save");
        commands.add("bye");
    }


    public final static void printMessage(String text) {
        System.out.println(text);
        System.out.println("____________________________________________________________\n");
    }

}

