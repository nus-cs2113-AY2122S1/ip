package duke.task;

public class Task {
    protected String description;
    protected boolean isDone;
    protected Character taskType;

    /**
     * @param description task information
     * @param isDone true of false
     */
    public Task(String description,boolean isDone) {
        this.description = description;
        this.isDone = isDone;
        this.taskType = 'T';
    }

    /**
     *
     * @return "X" for task is done
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     *
     * @return task information
     */
    public String getDescription(){
        return description;
    }

    /**
     *
     * @return the type of task
     */
    public Character getTaskType(){
        return taskType;
    }

    /**
     *
     * @return a readable String for the task
     */
    public String toString(){
        return "["+ getTaskType() +"]" + "[" + getStatusIcon() + "] " + getDescription();
    }

    public void markAsDone(){
        this.isDone = true;
    }

}


