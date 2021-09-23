package duke;

import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.CommandException;
import duke.file.FileHandler;
import duke.parser.Parser;
import duke.parser.ParserException;
import duke.task.TaskManager;
import duke.ui.Ui;


public class Duke {

    private Ui ui;
    private FileHandler fileHandler;
    private TaskManager taskManager;

    private String fileName;
    private String fileDirectory;

    public Duke(String fileDirectory, String fileName) {
        this.fileName = fileName;
        this.fileDirectory = fileDirectory;
        ui = new Ui();
        taskManager = new TaskManager();
        fileHandler = new FileHandler(fileDirectory);
        ui.printFileLoadingMessage(fileName);
        try{
            taskManager.processContentsFromFile(fileHandler.load(fileName));
        }catch(DukeException e){
            ui.printDukeExceptionMessage(e);
        }
        ui.printFileLoadingDoneMessage();
    }

    /**
     * Main function to run the bot.
     *
     * @param args Arguments from console input
     */
    public static void main(String[] args) {
        new Duke("data","duke.txt").run();
    }

    private void executeCommand(Command command){
        try{
            command.setTaskManager(taskManager);
            command.execute();
            ui.printLine();
            if(command.hasDataChange()){
                fileHandler.writeToFile(fileName,taskManager.toString());
            }
        }catch(CommandException e){
            ui.printCommandExceptionMessage(e);
        }catch(DukeException e){
            ui.printDukeExceptionMessage(e);
        }catch(NullPointerException e){
            ui.printMessage(e.getMessage());
        }
    }

    private void run(){
        ui.printWelcomeMessage();
        Parser parser = new Parser();
        Command command = null;
        do{
            try{
                command = parser.parseCommand(ui.getUserInput());
                executeCommand(command);
            }catch(ParserException e){
                ui.printParserExceptionMessage(e);
            }
        }while(!ByeCommand.isExit(command));
        ui.printExitMessage();
    }

}
