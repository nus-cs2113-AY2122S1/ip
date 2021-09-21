package duke.task;

public class Todo extends Task {
    /**
     * Constructor for Todo class.
     *
     * @param description The task description.
     */
    public Todo(String description) {
        super(description, Task.TYPE_TODO);
    }

    /**
     * Returns string in file entry format.
     * eg. <taskType> | <isDone> | <description>
     *
     * @return The formatted string.
     */
    @Override
    public String toFileString() {
        return generateFileString(new String[]{
                Character.toString(taskType),
                Integer.toString((isDone) ? 1 : 0),
                description
        });
    }
}
