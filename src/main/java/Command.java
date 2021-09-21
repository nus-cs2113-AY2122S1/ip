
public class Command {
    protected TaskList tasksList;
    private int targetIndex = -1;
    private String targetTask = null;

    protected Command() {
    }

    public Command(int targetIndex) {
        this.setTargetIndex(targetIndex);
    }

    public CommandResult execute() {
        throw new UnsupportedOperationException("Implemented by Child Class");
    }

    public void setData(TaskList tasksList) {
        this.tasksList = tasksList;
    }

    protected Task getTargetTask() throws IndexOutOfBoundsException {
        return tasksList.get(getTargetIndex());
    }

    public int getTargetIndex() {
        return targetIndex;
    }

    public void setTargetIndex(int targetIndex) {
        this.targetIndex = targetIndex;
    }
}
