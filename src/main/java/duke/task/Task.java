package duke.task;

import duke.exceptions.EmptyField;
import duke.exceptions.IllegalOperation;
import duke.ui.MessageBubble;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public abstract class Task {
    protected String description = "";
    protected boolean status = false;
    protected static String SYMBOL = " ";
    protected LocalDateTime time;
    public String symbolSetTime = "";
    protected DateTimeFormatter saveFormatter = DateTimeFormatter.ofPattern("d/M/yyyy kk[mm]");
    protected DateTimeFormatter readFormatter = DateTimeFormatter.ofPattern("kk:mm, MMM dd yyyy");

    public String getSYMBOL() {
        return SYMBOL;
    }

    public String getDescription() {
        return description;
    }

    /**
     * Set description of the task
     *
     * @param description task description
     * @throws EmptyField if description is blank
     */
    public void setDescription(String description) throws EmptyField {
        if (description.isBlank()) {
            throw new EmptyField();
        }
        this.description = description;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    /**
     * Returns the time variable value of the Task
     *
     * @return time
     * @throws IllegalOperation if the task does not contain a time variable
     */
    public String getTime() {
        return time.format(readFormatter);
    }

    /**
     * Set the time variable of the Task
     *
     * @param time time
     * @throws IllegalOperation if time is badly formatted
     * @throws EmptyField       if time is empty
     */
    public void setTime(String time) {
        try {
            this.time = LocalDateTime.parse(time, saveFormatter);
        } catch (DateTimeParseException e) {
            MessageBubble.printMessageBubble("Time format error. Example: 15/9/2021 2142" +
                    "\nBy default set to tomorrow");
            this.time = LocalDateTime.now().plusDays(1);
        }
    }

    /**
     * Set the status of the Task to a new status.
     *
     * @param status new status
     * @throws IllegalOperation if new status == current status
     */
    public void setDone(boolean status) throws IllegalOperation {
        if (this.status == status) {
            throw new IllegalOperation();
        } else {
            this.status = status;
        }
    }

    /**
     * Returns a String that can be used to store and restore the information about the Task.
     *
     * @return Save format String
     */
    public String getSaveFormat() {
        return getSaveFormat(new String[0]);
    }

    public String getSaveFormat(String additionalInfo) {
        return getSaveFormat(new String[]{additionalInfo});
    }

    public String getSaveFormat(String[] additionalInfo) {
        String temp = String.format("%s | %s | %s", this.getSYMBOL(), this.status ? "1" : "0", description);
        for (String info : additionalInfo) {
            temp = temp + " | " + info;
        }
        return temp;
    }

    @Override
    public String toString() {
        String classIndicator = this.getClass().getSimpleName().substring(0, 1);
        String statusIndicator = status ? "X" : " ";
        return String.format("[%s][%s] %s", classIndicator, statusIndicator, description);
    }
}
