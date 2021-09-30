package duke.Command;

import duke.ErrorHandling.CommandException;

/**
 * Represent multiple simple one word commands that mainly just print out messages
 */
public class CommandGuide extends Command{

    /**
     * Constructor for CommandGuide
     *
     * @param taskInput Command input by user
     */
    public CommandGuide(String taskInput){
        super(taskInput);
    }

    /**
     * Print out a fixed message depending on user input or hand over processing of input to ArtCommand
     */
    @Override
    public void executeCommand(){
        if(taskInput.startsWith(COMMAND_ECHO_ART)){
            ArtCommand artCommand = new ArtCommand(taskInput);
            try {
                artCommand.handleArtCommand();
            } catch (CommandException e) {
                e.handleException();
            }
            return;
        }
        switch(taskInput) {
        case COMMAND_HELP:
            System.out.println(MESSAGE_HELP);
            break;
        case COMMAND_LIST_HELP:
            System.out.println(MESSAGE_LIST_HELP);
            break;
        case COMMAND_EVENT_HELP:
            System.out.println(MESSAGE_EVENT_HELP);
            break;
        case COMMAND_DEADLINE_HELP:
            System.out.println(MESSAGE_DEADLINE_HELP);
            break;
        }
    }
}
