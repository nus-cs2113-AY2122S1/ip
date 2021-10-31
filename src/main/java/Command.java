public abstract class Command {
    /**
     * Executes a particular operation on the TaskList object.
     * Abstract method which will be overridden for different specific use
     * by child classes.
     *
     * @param tl TaskList object in which task are stored in.
     */
    public abstract void execute(TaskList tl);
}
