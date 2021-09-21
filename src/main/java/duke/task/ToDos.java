package duke.task;

public class ToDos extends Task {

    /**
     * A constructor to create task of type todo.
     *
     * @param taskName name of task.
     */
    public ToDos(String taskName) {
        super(taskName);
    }

    /**
     * Returns a String format of task to be printed.
     *
     * @return String of type, isDone and task name.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns a String format of task to be stored in storage text file.
     *
     * @return String of type, isDone and task name.
     */
    @Override
    public String storageText () {
        return "T" + super.storageText();
    }
}
