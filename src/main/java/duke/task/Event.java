package duke.task;

/**
 * A class representing an event with a name, date and time of event
 */

public class Event extends Task {

    private String name; //name of duke.task only (eg return book)
    protected String at; //time

    /**
     * Constructor for {@code Event} class
     *
     * @param name Name of event
     * @param at Date and time of event
     */
    public Event(String name, String at) {
        description = name + " (at: " + at + ")"; //name + time
        this.at = at;
        type = "E";
        this.name = name;
    }


    /**
     * Prints added event message
     */
    @Override
    public void printTaskDisplay() {
        System.out.println("Got it. I've added this task:");
        System.out.println("[E] [ ] " + description);
    }

}
