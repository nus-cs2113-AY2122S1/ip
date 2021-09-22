package commands;

import parser.DateParser;
import storage.Storage;
import tasks.Event;
import tasks.Task;
import ui.Ui;

import java.util.ArrayList;
import java.util.Date;

/**
 * Represents the Event command. Helps to do all operations of the event command such as
 * creating the event object and does all the error handling such as invalid date.
 */

public class EventCommand extends Command {
    private static final String atNotProvided = "I could not find '/at dd/MM/YYYY HHmm' in your command!";
    private static final String eventTitleNotProvided = "I could not find the title of your event!";
    public static final String commandSyntax = "Command Syntax: event <event name> /at <date in dd/MM/yyyy HHmm>";

    public String eventName;
    public String at;

    public EventCommand(String command, String eventName, String at) {
        super(command);
        this.eventName = eventName;
        this.at = at;
    }

    @Override
    public String help() {
        return commandSyntax;
    }

    @Override
    public void execute(Ui ui, ArrayList<Task> tasks, Storage storage) {
        Date dateObj = DateParser.stringToDateTime(at);
        // Handle case it user did not provide date
        if (dateObj == null) {
            ui.customPrint(atNotProvided + "\n" + help());
        } else if (eventName.equals("")) {
            ui.customPrint(eventTitleNotProvided + "\n" + help());
        } else {
            Event event = new Event(eventName, dateObj);
            tasks.add(event);
            ui.customPrint(String.format(addTaskMessage, event, tasks.size()));
            storage.saveData(ui, tasks);
        }
    }
}
