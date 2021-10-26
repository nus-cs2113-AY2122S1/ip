package duke.templar;

/**
 * Defines the type of task known as todo, with a description
 */
public class ToDo extends Task
{

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString() + " ";
    }
}