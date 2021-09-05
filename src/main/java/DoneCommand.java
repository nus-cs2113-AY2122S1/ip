public class DoneCommand extends Command {
    private int taskNumber;

    /**
     * Class done command constructor.
     *
     * @param taskNumber ID of task stored in list.
     */
    public DoneCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Runs a command to mark task as completed.
     *
     * @throws DukeException If an invalid task number is provided.
     */
    @Override
    public void runCommand() throws DukeException {
        TaskManager.markAsCompleted(taskNumber);
    }
}
