package duke.tasks;

public class Todo extends Task {

    /**
     * Default constructor
     *
     * @param todo   description of todo
     * @param isDone whether the todo is done or not
     */
    public Todo(String todo, boolean isDone) {
        super(todo);
        this.isDone = isDone;
    }

    /**
     * Converts the task into a String fit for being stored into a data file
     *
     * @return String consisting of all the task information to be stored in a data file
     */
    @Override
    public String toData() {
        return "T | " + ((isDone) ? 1 : 0) + " | " + task;
    }

    /**
     * Converts the task into a String to be printed for the user to see
     *
     * @return String consisting of all the task information to be shown to user
     */
    @Override
    public String toString() {
        return "[T]" + (isDone ? "[X] " : "[ ] ") + task;
    }
}
