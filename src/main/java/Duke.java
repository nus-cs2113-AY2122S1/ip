import commands.Command;
import exception.DukeException;
import task.type.TaskList;
import ui.UI;
import java.io.FileNotFoundException;
import parser.Parser;
import storage.Storage;

public class Duke {
    private static final String FILEPATH = "data/saveData.txt";
    private Storage storage;
    private TaskList tasks;

    public Duke(String filePath){
        storage = new Storage();
        try{
            tasks = new TaskList(storage.loadData());
        } catch (FileNotFoundException e){
            UI.printErrorMessageFileNotFound();
            tasks = new TaskList();
        }
    }

    public void run(){
        UI.printGreeting();
        boolean isExit = false;
        while(!isExit){
            try {
                String input = UI.getInput();
                Command c = Parser.parse(tasks, input);
                c.execute(tasks, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
            }
        }
    }

    public static void main(String[] args) {
        new Duke(FILEPATH).run();
    }
}