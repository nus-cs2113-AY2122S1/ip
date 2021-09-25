package duke.Command;

import duke.ArtBot.Logo;

public class CommandGuide extends Command{


    public CommandGuide(String userInput){
        super(userInput);
    }

    @Override
    public void executeCommand(){
        if(taskInput.startsWith(COMMAND_ECHO_ART)){
            ArtCommand artCommand = new ArtCommand(taskInput);
            artCommand.handleArtCommand();
            return;
        }
        switch(taskInput) {
        case COMMAND_HELP:
            System.out.println(Logo.divider + MESSAGE_HELP + Logo.dividerWithoutNewLine);
            break;
        case COMMAND_LIST_HELP:
            System.out.println(Logo.divider + MESSAGE_LIST_HELP + Logo.dividerWithoutNewLine);
            break;
        case COMMAND_EVENT_HELP:
            System.out.println(Logo.divider + MESSAGE_EVENT_HELP + Logo.dividerWithoutNewLine);
            break;
        case COMMAND_DEADLINE_HELP:
            System.out.println(Logo.divider + MESSAGE_DEADLINE_HELP + Logo.dividerWithoutNewLine);
            break;
        }
    }
}
