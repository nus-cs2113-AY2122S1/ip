public class ToDo extends Task {

    /**
     * Create a ToDo Task.
     *
     * @param name The name of task.
     */
    public ToDo(String name) {
        super(name);
    }

    /**
     * Get the Item type which is T in this case.
     * @return A string "T".
     */
    @Override
    public String getItemType() {
        return "T";
    }
}
