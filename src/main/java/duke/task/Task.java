package duke.task;

/**
 * Implements the abstract class Task, which minimally has a description and
 * a status of whether it is done or not.
 */
abstract public class Task {

    protected String description;
    protected boolean isDone;


    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Sets isDone to true.
     */
    public void setDone() {
        this.isDone = true;
    }

    /**
     * Sets isDone to the input value.
     * @param isDone the status of the Task
     */
    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Finds if the Task contains the given search term.
     * @param search the search term
     * @return true if the Task contains the search term; false otherwise
     */
    public boolean contains(String search) {
        return description.toLowerCase().contains(search.toLowerCase());
    }

    /** The formatted String to be stored in the external file */
    public abstract String taskString();
    
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Task) {
            @SuppressWarnings("unchecked")
            Task t = (Task) obj;
            return this.description.equals(t.description) && this.isDone == (t.isDone);
        }
        return false;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}

