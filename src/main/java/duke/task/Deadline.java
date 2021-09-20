package duke.task;

import duke.exceptions.EmptyField;
import duke.ui.MessageBubble;

public class Deadline extends Task {
    public String symbolSetTime = "/by";
    protected static String SYMBOL = "D";

    /**
     * Convenience Deadline constructor using raw values
     *
     * @param description description of the Deadline
     * @param time        latest time to complete the Deadline
     * @throws EmptyField if one or more param is missing or of wrong format
     */
    public Deadline(String description, String time) throws EmptyField {
        setDescription(description);
        setTime(time);
    }

    /**
     * Convenience Deadline constructor using raw values
     *
     * @param description description of the Deadline
     * @param done        status of the Deadline
     * @param time        latest time to complete the Deadline
     * @throws EmptyField if one or more param is missing or of wrong format
     */
    public Deadline(String description, boolean done, String time) throws EmptyField {
        setDescription(description);
        setTime(time);
        setStatus(done);
    }

    @Override
    public String getSYMBOL() {
        return SYMBOL;
    }

    @Override
    public String getSaveFormat() {
        return super.getSaveFormat(time.format(saveFormatter));
    }

    @Override
    public void editTaskInteractive() {
        try {
            MessageBubble.printMessageBubble(String.format("Updated description for the deadline?\n(original: %s)", this.getDescription()));
            setDescription(input.nextLine());
            MessageBubble.printMessageBubble(String.format("Updated deadline time?\n(original: %s)", this.time.format(saveFormatter)));
            setTime(input.nextLine());
            MessageBubble.printMessageBubble("Updated Deadline:\n" + this);
        } catch (EmptyField e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        String classIndicator = this.getClass().getSimpleName().substring(0, 1);
        String statusIndicator = status ? "X" : " ";
        return String.format("[%s][%s] %s (by %s)", classIndicator, statusIndicator, description, getTime());
    }
}
