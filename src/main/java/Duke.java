import command.Command;
import exceptions.DukeInvalidArgumentException;
import parser.Parser;
import storage.Storage;
import task.TaskList;
import ui.UI;

import java.io.FileNotFoundException;

/**
 * Represents Duke class.
 */
public class Duke {
    private static final String FILEPATH = "data/saveData.txt";
    private Storage storage;
    private TaskList tasks;

    /**
     * Loads save data if the save file exists.
     * If file npt available, create one.
     */
    public Duke(String filePath){
        storage = new Storage();
        try{
            tasks = new TaskList(storage.loadData());
        } catch (FileNotFoundException e){
            UI.printFileNotFoundMessage();
            tasks = new TaskList();
        }
    }

    /**
     * Parses user input and executes it until "bye" command is encountered.
     */
    public void run(){
        UI.printGreetMessage();
        boolean isExit = false;
        while(!isExit){
            try {
                String input = UI.getUserInput();
                Command c = Parser.parse(tasks, input);
                c.execute(tasks, storage);
                isExit = c.isExit();
            } catch (DukeInvalidArgumentException e) {
            }
        }
    }

    /**
     * Main method that runs Duke when called.
     */
    public static void main(String[] args) {
        new Duke(FILEPATH).run();
    }
}