import commands.Command;
import commands.Parser;
import ui.TextUI;
import data.Storage;
import data.TaskList;

/**
 * Esteban is a command-line based program that acts as a task tracker,
 * helping users manage their todos, events and deadlines
 *
 * @author exetr
 * @version 0.2
 */
public class Esteban {
    /**
     * Main method and entrypoint for the program
     * @param args This is the list of arguments specified on runtime
     */
    public static void main(String[] args) {
        TextUI ui = new TextUI();
        Storage dataStore = new Storage();
        TaskList tasks = new TaskList(dataStore.read());
        // Continue to read, parse and execute user commands until exit command is issued
        while(true) {
            String userInput = ui.getCommand();
            Command userCommand = Parser.parseCommand(userInput);
            userCommand.execute(ui, tasks, dataStore);
            if(userCommand.isStop()) {
                break;
            }
        }
    }
}
