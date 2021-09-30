package Task;

public class Task {
    /** The name of the task**/
    protected String content;
    /** The complement status of the task **/
    protected boolean Done;

    /**
     * A public constructor to initialized the Task.
     * @param content the whole information of the task
     */
    public Task(String content) {
        this.content = content;
        this.Done = false;
    }

    public String getContent() {
        return this.content;
    }
    /**
     * The methods to mark the task as done.
     */
    public void complete(){
        this.Done = true;
    }

    /**
     * @return The complement status of the task.
     */
    public String getInformation() {
        return (Done ? "X" : " ");
    }

    /**
     * @return The whole status of teh task including name and complement.
     */
    @Override
    public String toString() {
        return "[" + getInformation() + "]" + content;
    }
}
