package duke.Command;

import duke.ArtBot.Logo;
import duke.ErrorHandling.CommandException;
import duke.ErrorHandling.ErrorStaticString;

public class EchoCommand extends Command{


    public EchoCommand(String userInput){
        super(userInput);
    }

    public void executeCommand() throws CommandException {
        String removeCommand = taskInput.replaceFirst(COMMAND_ECHO_ART, EMPTY_STRING).trim();
        if(removeCommand.isEmpty()){
            throw new CommandException(ErrorStaticString.ERROR_EMPTY_ECHO_INPUT);
        }
        System.out.println(removeCommand);
    }

}
