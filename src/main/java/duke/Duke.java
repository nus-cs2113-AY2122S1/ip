package duke;

import duke.ErrorHandling.CommandException;
import duke.Parser.Parser;
import duke.Storage.Storage;
import duke.TaskList.TaskList;
import duke.Ui.Ui;

public class Duke {

    private final TaskList tasks;
    private final Ui ui;

    public Duke(){
        ui = new Ui();
        Storage storage = new Storage();
        tasks = new TaskList(storage.load());
    }

    public void run(){
        ui.Greetings();
        boolean isExit = false;
        do{
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Parser parser = new Parser(fullCommand, tasks);
                isExit = parser.isExit();
                if(!isExit) {
                    parser.handleInput();
                }
            }catch(CommandException e){
                ui.showError(e.getErrorMessage());
            }finally {
                ui.showLine();
            }
        }while (!isExit);
        ui.Farewell();
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
