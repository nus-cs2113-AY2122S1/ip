import commands.Command;
import commands.Parser;
import ui.TextUI;
import data.Storage;
import data.TaskList;

public class Esteban {
    public static void main(String[] args) {
        TextUI ui = new TextUI();
        Storage dataStore = new Storage();
        TaskList tasks = new TaskList(dataStore.read());

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
