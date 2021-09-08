package duke.task;

public class Deadline extends Task {

    public Deadline(String description) {
        super(description);
    }

    @Override
    public String getType(){
        return ("D");
    }

    @Override
    public String getDescription() {
        int byPos = description.indexOf("/by");
        return (description.substring(0,byPos) + "(by: " + description.substring(byPos + 3).trim() + ")");
    }
}
