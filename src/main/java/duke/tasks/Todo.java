package duke.tasks;

public class Todo extends Task {

    private String type = "[T]";
    public Todo(String description) {
        super(description);
    }

    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    public String getType(){
        return this.type;
    }

    public String getDueDate(){
        return null;
    }

    public String toString() {
        return type + super.toString();
    }

}

