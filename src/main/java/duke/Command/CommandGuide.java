package duke.Command;

import duke.ArtBot.Logo;

public class CommandGuide extends Command{


    public CommandGuide(String taskInput){
        super(taskInput);
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
