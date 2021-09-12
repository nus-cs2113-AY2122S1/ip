package kitty.task;

import kitty.Kitty;
import kitty.KittyException;
import kitty.Parser;
import kitty.io.IO;

public class Event extends Task{
    protected String eventDate;

    public Event(String taskName, String eventDate) {
        super(taskName);
        this.eventDate = eventDate;
    }

    //Getter
    public String getEventDate() {
        return eventDate;
    }

    // Methods
    public static void addEventTask(String line) throws KittyException {
        if(!Parser.hasEventDate(line)) {
            throw new KittyException("Event formatting is incorrect!");
        } else {
            try {
                // Get Event Name
                String taskName = Parser.getEventTaskName(line);

                // Get Event Date
                String EventDate = Parser.getEventDate(line);

                // Add Event Task
                Kitty.tasks.add(new Event(taskName, EventDate));
//                IO.writeNewLine("E|0|" + taskName + "|" + EventDate);
            } catch (KittyException e) {
                throw e;
            }
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " [at: " + eventDate + "]";
    }
}
