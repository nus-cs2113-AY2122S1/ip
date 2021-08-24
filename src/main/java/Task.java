public class Task {
    private String description; // description of the task
    private boolean isDone; // indicates whether the task is completed

    public Task(String description) {
        // constructor for Task class
        setDescription(description);
        setDone(false);
    }

    public String getDescription() {
        // returns the task description
        return description;
    }

    public void setDescription(String description) {
        // setter for the task description
        this.description = description;
    }

    public boolean isDone() {
        // getter for isDone attribute
        return isDone;
    }

    public void setDone(boolean done) {
        // setter for isDone attribute
        isDone = done;
    }

    public String getStatus() {
        //returns the icon to indicate whether the task is completed
        if (isDone()) {
            return "X";
        }
        return " ";
    }

    public void printTask() {
        //prints a specific task in a format along with the status
        System.out.println("[" + getStatus() + "] " + getDescription());
    }
}