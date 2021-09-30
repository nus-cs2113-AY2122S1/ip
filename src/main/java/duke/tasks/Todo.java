package duke.tasks;

public class Todo extends Task {

    private String type = "[T]";

    /**
     * Represents a Todo made by the user.
     *
     * @param description Description of Todo.
     */

    public Todo(String description) {
        super(description);
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

