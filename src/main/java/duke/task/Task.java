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
    protected LocalDateTime time;
    public String symbolSetTime = "";
    protected DateTimeFormatter saveFormatter = DateTimeFormatter.ofPattern("d/M/yyyy kk:mm");
    protected DateTimeFormatter readFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy, kk:mm");

    public String getDescription() {
        return description;
    }

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

    public String getTime() {
        return time.format(readFormatter);
    }

    public void setTime(String time) {
        try {
            this.time = LocalDateTime.parse(time, saveFormatter);
        } catch (DateTimeParseException e) {
            MessageBubble.printMessageBubble("Time format error, set default to tmr");
            this.time = LocalDateTime.now().plusDays(1);
        }
    }

    public void setDone(boolean status) throws IllegalOperation {
        if (this.status == status) {
            throw new IllegalOperation();
        } else {
            this.status = status;
        }
    }

    public String getSaveFormat() {
        return String.format("%s | %s | %s", " ", status? "1":"0", description);
    }

    @Override
    public String toString() {
        String classIndicator = this.getClass().getSimpleName().substring(0,1);
        String statusIndicator = status ? "X" : " ";
        return String.format("[%s][%s] %s", classIndicator, statusIndicator, description);
    }
}
