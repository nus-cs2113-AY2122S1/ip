package seedu.tojava.Duke;
public class Todo {

    protected boolean isDone;
    protected String description;

    public Todo(String description){
        this.description = description;
        this.isDone = false;
    }


    public String getStatusIcon(){
        return (isDone ? "X":" ");
    }

    public String getDescription(){
        return description;
    }

    public void markAsDone(){
        isDone = true;
    }

    public String returnType(){
        return "T";
    }
}
