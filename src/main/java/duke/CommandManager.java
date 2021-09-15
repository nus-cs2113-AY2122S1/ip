package duke;

public class CommandManager implements CommandInterface{

    private static final String COMMAND_HELP = "!help";
    private static final String COMMAND_LIST_HELP = "!list";
    private static final String COMMAND_EVENT_HELP = "!event";
    private static final String COMMAND_DEADLINE_HELP = "!deadline";
    private static final String COMMAND_ECHO = "!echo";
    private static final String MESSAGE_HELP =  "List of Commands:\n" +
                                                "   echo - Repeat whatever was typed - !echo to repeat in art form\n" +
                                                "   list - Display List - !list for details\n" +
                                                "   todo - Add ToDo Task\n" +
                                                "   event - Add Event Task - !event for details\n" +
                                                "   deadline - Add Deadline Task - !deadline for details\n" +
                                                "   bye - Shut Down\n";
    private static final String MESSAGE_LIST_HELP = "list displays all tasks\n" +
                                                    "list todo displays all todo tasks\n" +
                                                    "list event displays all event tasks\n" +
                                                    "list deadline displays all deadline tasks\n";
    private static final String MESSAGE_EVENT_HELP = "event command requires a timing indicated using \"at\" [timing]";
    private static final String MESSAGE_DEADLINE_HELP = "deadline command requires a end time indicated using \"by\"[end time]";

    private final String description;

    public CommandManager(String description){
        this.description = description;
    }

    private void handleArtCommand(){
        String removeCommand = description.replaceFirst(COMMAND_ECHO,"").trim().toUpperCase();
        System.out.println(removeCommand);
        ArtBot artBot = new ArtBot(removeCommand);
        try {
            artBot.drawArt();
        }catch (CommandException e){
            e.handleException();
        }
    }

    public void handleCommand(){
        if(description.startsWith(COMMAND_ECHO)){
            handleArtCommand();
            return;
        }
        switch(description) {
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
