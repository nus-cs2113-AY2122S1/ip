package duke.parser;

import java.time.LocalDate;

public abstract class Parser {
    protected String command;
    protected String description;
    protected String timeField;
    protected String formattedTimeField;
    protected LocalDate localDate;
    protected int taskIndex;
    protected boolean done = false;
    protected String fileFormat;

    public String getFormattedTimeField() {
        return formattedTimeField;
    }

    public String getCommand() {
        return command;
    }

    public String getDescription() {
        return description;
    }

    public int getTaskIndex() {
        return taskIndex;
    }

    public boolean isDone() {
        return done;
    }

    public String getFileFormat() {
        return fileFormat;
    }
}
