package duke.commands;

import duke.task.Event;

public class EventCommand extends AddTaskCommand {
    public static final String COMMAND_WORD = "event";

    public EventCommand(String description, String at) {
        toAdd = new Event(description, at);
    }
}
