package duke.Command;

import duke.ErrorHandling.CommandException;
import duke.ErrorHandling.ErrorStaticString;

/**
 * Represent a command the echo back what was input by user
 */
public class EchoCommand extends Command{

    /**
     * Constructor for Echo Command
     *
     * @param userInput Command input by user to process
     */
    public EchoCommand(String userInput){
        super(userInput);
    }

    /**
     * Remove command from string and echo back remaining string
     *
     * @throws CommandException if string to echo is empty
     */
    public void executeCommand() throws CommandException {
        String removeCommand = taskInput.replaceFirst(COMMAND_ECHO, EMPTY_STRING).trim();
        if(removeCommand.isEmpty()){
            throw new CommandException(ErrorStaticString.ERROR_EMPTY_ECHO_INPUT);
        }
        System.out.println(removeCommand);
    }

}
