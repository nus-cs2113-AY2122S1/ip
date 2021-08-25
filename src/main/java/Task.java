public class Task {

    /**
     * Status of whether the task is completed
     */
    private boolean isDone;
    private String name;


    public Task(String name) {
        this.name = name;
        isDone = false;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
        if (this.isDone) {
            System.out.println("Nice! I've marked this task as done:");
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns a string depending of the isDone status. If the task isDone is true, "X" is return. Else " " is return.
     *
     * @return isDone status icon
     */
    public String getStatusIcon() {
        String isDoneFlag = " ";
        if (isDone) {
            isDoneFlag = "X";
        }
        return isDoneFlag;
    }

    /**
     * Prints the status of the task in a given format. The format is "[ ]TaskName".
     */
    public void printStatus() {
        System.out.printf("[%s] %s\n", getStatusIcon(), getName());
    }
}
