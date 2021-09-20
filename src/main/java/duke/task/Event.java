package duke.task;

import duke.exceptions.EmptyField;
import duke.ui.MessageBubble;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    public String symbolSetTime = "/at";
    protected static String SYMBOL = "E";
    protected LocalDateTime endTime;

    /**
     * Convenience constructor using raw values.
     *
     * @param description description of the Event
     * @param startTime   start time of the Event
     * @param endTime     end time of the Event
     * @throws EmptyField if one or more param is missing or of wrong format
     */
    public Event(String description, String startTime, String endTime) throws EmptyField {
        setDescription(description);
        setTime(startTime);
        setEndTime(endTime);
    }

    /**
     * Convenience constructor using raw values.
     *
     * @param description description of the Event
     * @param done        status of the Event
     * @param startTime   start time of the Event
     * @param endTime     end time of the Event
     * @throws EmptyField if one or more param is missing or of wrong format
     */
    public Event(String description, boolean done, String startTime, String endTime) throws EmptyField {
        setDescription(description);
        setTime(startTime);
        setEndTime(endTime);
        setStatus(done);
    }

    public String getEndTime() {
        return endTime.format(readFormatter);
    }

    private void setEndTime(String endTime) {
        try {
            this.endTime = LocalDateTime.parse(endTime, saveFormatter);
        } catch (DateTimeParseException e) {
            MessageBubble.printMessageBubble("Event end time format error. Example: 15/9/2021 2142" +
                    "\nBy default set to tomorrow");
            this.endTime = LocalDateTime.now().plusDays(1); // if format wrong, set to 1 day from now
        }
    }

    @Override
    public String getSYMBOL() {
        return SYMBOL;
    }

    @Override
    public String getSaveFormat() {
        return super.getSaveFormat(new String[]{time.format(saveFormatter), endTime.format(saveFormatter)});
    }

    @Override
    public void editTaskInteractive() {
        try {
            MessageBubble.printMessageBubble(String.format("New description for the event?\n(original: %s)", this.getDescription()));
            setDescription(input.nextLine());
            MessageBubble.printMessageBubble(String.format("New event start time?\n(original: %s)", this.time.format(saveFormatter)));
            setTime(input.nextLine());
            MessageBubble.printMessageBubble(String.format("New event end time?\n(original: %s)", this.endTime.format(saveFormatter)));
            setEndTime(input.nextLine());
            MessageBubble.printMessageBubble("Updated Event:\n" + this);
        } catch (EmptyField e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns all information of the Event as a user-friendly String format
     *
     * @return all information about the Event
     */
    @Override
    public String toString() {
        String classIndicator = this.getClass().getSimpleName().substring(0, 1);
        String statusIndicator = status ? "X" : " ";
        return String.format("[%s][%s] %s (from %s to %s)", classIndicator, statusIndicator, description, getTime(), getEndTime());
    }
}