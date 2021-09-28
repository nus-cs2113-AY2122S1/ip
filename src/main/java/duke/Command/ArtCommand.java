package duke.Command;

import duke.ArtBot.ArtBot;

/**
 * Represent a command that echo string in graffiti form
 */
public class ArtCommand extends CommandGuide{

    /**
     * Constructor for ArtCommand
     *
     * @param userInput Command input by user to process
     */
    public ArtCommand(String userInput){
        super(userInput);
    }

    /**
     * Filter out string to echo in graffiti and hand over drawing to artbot class
     */
    public void handleArtCommand(){
        String removeCommand = taskInput.replaceFirst(COMMAND_ECHO_ART,EMPTY_STRING).trim().toUpperCase();
        System.out.println(removeCommand);
        ArtBot artBot = new ArtBot(removeCommand);
        artBot.drawArt();
    }
}
