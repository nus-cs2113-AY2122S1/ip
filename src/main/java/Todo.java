/**
 * Represents a task that is added to the list by a user. A Todo object contains a description
 * represented by a String and a done status represented by a boolean. This is all inherited
 * from superclass Task.
 * Inherits all methods from superclass Task.
 */
public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "  [T]" + super.toString();
    }

    @Override
    public String saveToFile() {
        // return format
        String printStatus;
        // return format
        if(this.isDone == true) {
            printStatus = "1";
        } else {
            printStatus = "0";
        }
        return "T " + printStatus + " " + description;
    }
    }

