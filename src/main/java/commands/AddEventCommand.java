package commands;

import parser.Parser;
import storage.Storage;
import tasks.Event;
import ui.TextUi;

import java.io.IOException;

public class AddEventCommand extends Command{

    public static final String COMMAND_WORD = "event";
    public static final String COMMAND_DESC = "Adds a task of \"EVENT\" type to the list";

    private final String eventParams;

    public AddEventCommand(String eventParams) {
        this.eventParams = eventParams;
    }

    /**
     * Adds an Event task to the TaskList and appends the task into the datafile
     */
    @Override
    public void execute() {
        try {
            String[] eventDescriptionAndTimeRange = Parser.splitEventDescriptionAndTimeRange(eventParams);
            String eventDescription = eventDescriptionAndTimeRange[0].trim();
            String eventTimeRange = eventDescriptionAndTimeRange[1].trim();
            if(eventDescription.equals("") || eventTimeRange.equals("")) {
                TextUi.showMissingTaskDescriptionMessage();
            } else {
                Event newEvent = new Event(eventDescription, eventTimeRange);
                taskList.addTask(newEvent);
                TextUi.showTaskAddedMessage(newEvent, taskList.getSize());
                Storage.appendToDataFile("E / 0 / " + eventDescription + " | " + eventTimeRange);
            }
        } catch (ArrayIndexOutOfBoundsException e){
            TextUi.showInvalidEventMessage();
        } catch (IOException e){
            TextUi.showIOExceptionMessage(e);
        }
    }
}
