public class Todo extends Task{

    /**
     * Creates a todo with task type [T].
     * @param name Name of the todo.
     */
    public Todo(String name) {
        super(name);
        //
        this.typeOfTask = "[T]";
    }

    /**
     * Returns task in a human-readable format.
     * @return [Type of Task][Completion Status] [Name of Task]
     */
    public String printTask() {
        return this.getTypeOfTask() + this.getDoneStatusAsSymbol() + " " + this.getName();
    }


}
