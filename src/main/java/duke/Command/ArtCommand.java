package duke.Command;

import duke.ArtBot.ArtBot;

public class ArtCommand extends CommandGuide{


    public ArtCommand(String userInput){
        super(userInput);
    }

    public void handleArtCommand(){
        String removeCommand = taskInput.replaceFirst(COMMAND_ECHO_ART,EMPTY_STRING).trim().toUpperCase();
        System.out.println(removeCommand);
        ArtBot artBot = new ArtBot(removeCommand);
        artBot.drawArt();
    }
}
