package duke.task;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
        this.type = 'T';
    }

    @Override
    public String saveString() {
        String output = "";
        if (isDone) {
            output += "[X] | ";
        } else {
            output += "[ ] | ";
        }
        output += type + " | " + description;
        return output;
    }
}
