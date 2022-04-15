package duke.task;

public class Task {

    private static final String DONE = "[X] ";
    private static final String NOT_DONE = "[ ] ";

    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone() {
        isDone = true;
    }

    /**
     * Checks for isDone in Task object and outputs [X] if true or [ ] if false.
     *
     * @param isDone boolean to see if task is done
     * @return output String that contains [X] or [ ]
     */
    public String printDone(boolean isDone) {
        String output;
        if (isDone) {
            output = DONE;
        } else {
            output = NOT_DONE;
        }
        return output;
    }

    public String toString() {
        return printDone(isDone) + description;
    }
}
