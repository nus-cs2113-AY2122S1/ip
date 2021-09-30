package duke.tasks;
/**
 * Represents a task made by the user.
 */
public class Task {
    protected String name;
    protected boolean done;

    /**
     * Constructor for Task
     *
     * @param input Description of task.
     */

    public Task(String input){
        this.name = input;
        this.done = false;
    }

    public void taskDone(){
        this.done = true;
    }

    public String getStatus(){
        return (done ? "X" : " ");
    }


    public String getName(){
        return this.name;
    }

    public String toString(){
        return "[T][" + getStatus() + "]" + name;
    }
}
