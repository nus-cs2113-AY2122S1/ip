package tasks;

public class Task {
    protected String name;
    protected boolean done;

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

    public String toString(){
        return "[T][" + getStatus() + "]" + name;
    }
}
