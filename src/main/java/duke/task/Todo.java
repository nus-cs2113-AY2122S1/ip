package duke.task;

public class Todo {
    private String name;
    private boolean isDone = false;

    /**
     * Returns a Todo object.
     *
     * @param name Name of the Todo.
     */
    public Todo(String name) {
        setName(name);
    }

    /**
     * Returns the name of the Todo.
     *
     * @return Name of the Todo.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the Todo.
     *
     * @param name Name of the Todo.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns status of the todo, whether is it done or not.
     *
     * @return Status of the todo.
     */
    public boolean getIsDone() {
        return isDone;
    }

    /**
     * Sets status of the todo to true.
     */
    public void setIsDone() {
        isDone = true;
    }

    /**
     * Returns a formatted string which contains information about the todo.
     *
     * @return Formatted string which contains information about the todo.
     */
    public String toString() {
        String boolString = isDone ? "X" : " ";
        return String.format("[T][%s] %s", boolString, name);
    }

}
