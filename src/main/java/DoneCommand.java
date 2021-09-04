public class DoneCommand extends Command {
    private int taskNumber;

    public DoneCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void runCommand() throws DukeException {
        TaskManager.markAsCompleted(taskNumber);
    }
}
