package duke.task;

public class Task {

    /*ATTRIBUTES*/

    public String type = " "; //empty for basic duke.task, T: todo, D: deadline, E: event
    public String description;
    protected boolean isDone;


    /*CONSTRUCTOR*/

    public Task() {
    }

    public Task(String description) {

        this.description = description;
        this.isDone = false;
    }


    /*METHODS*/

    //setter
    public void setDone(boolean done) {
        isDone = done;
    }

    //set icon as "X" for done, " " for not done
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done duke.task with X
    }

    public void printTaskNotif() {
        System.out.println("added: " + description);
    }

}
