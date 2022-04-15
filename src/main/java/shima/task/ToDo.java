package shima.task;

//Stores task without specific time
public class ToDo extends Task {
    public ToDo() {
        super();
    }

    public ToDo(String task) {
        this.task = task;
        this.isDone = false;
    }

    /**
     * Getter for the class type, retrieved from the enum TaskType
     *
     * @return Returns the type of the current class, 'T' for to-do class type
     */
    public String getClassType() {
        return TaskType.T.toString();
    }

    /**
     * Method inherits from its parent class, it has no meaning in this to-do class
     *
     * @return Returns a null value since to-do stores no time
     */
    public String getTime() {
        return "";
    }

    /**
     * Formats the string when printing a to-do task
     *
     * @return Returns the string with fixed format that contains the task description
     */
    @Override
    public String toString() {
        return task;
    }
}
