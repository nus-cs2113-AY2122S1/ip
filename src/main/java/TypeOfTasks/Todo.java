package TypeOfTasks;

/**
 * A type of task with description, checkbox.
 */
public class Todo extends Task {
    protected String tag = "T";

    /**
     * Initialises Todo object with its description.
     * @param description The Todo's description.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the task's tag to identify type of task
     * If the task is a Todo,Deadline,Event it returns T,D and E accordingly
     *
     * @return task's tag
     */
    public String getTag() {
        return tag;
    }
    /**
     * Returns null as Todo's do not have an extra description for by or at.
     *
     * @return null as Todo's does not have extra information other than its description.
     */
    public String getInfo() {
        return null;
    }

    /**
     * Prints all the details of the Todo.
     *
     * @param task The specific task in the entire tasklist.
     * @param index The index of the task getting printed, Base 1.
     */
    public void printTaskDetails(Task task, int index) {
        System.out.println(index + ".[T][" + (task.getStatus()) + "] "+ task.getDescription());
    }
}
